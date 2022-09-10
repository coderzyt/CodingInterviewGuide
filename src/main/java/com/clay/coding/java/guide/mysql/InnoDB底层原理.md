现在有这样一个 SQL 语句 `update user set name='zhangsan' where id = 11;` ，执行的流程和[上篇文章](https://mp.weixin.qq.com/s?__biz=MzI5MzQ2MDg4Nw==&mid=2247483778&idx=1&sn=b8818701f0575f049b3050fd798ce9a6&scene=21#wechat_redirect)中提到的 selelct 一样，最终由执行器来调用 InnoDB 存储引擎的接口。

![图片](https://mmbiz.qpic.cn/mmbiz_png/9YwUokWE01ywrJ1KlwRDUuKLJWzTNzTyJu9oUZG67icjIabaicWb8m7vHKu3n6OzvsIz9MB2IoP1UMA7AaMFQbeg/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1 "image.png")

# Buffer Pool：MySQL 的数据存在哪里？

**不知道你有没有看过 MySQL 中的数据在电脑上是怎么保存的？**我们平时创建的表，其实有一个表空间的概念，在磁盘上就对应「表明.ibd」这样一个磁盘文件。你可以用 `show variables like 'datadir';` 这个命令看看存储数据的目录在哪里，就能看到这些 `.ibd` 文件了。

那数据存在磁盘上，**每次执行增删改查操作的时候都直接修改磁盘上的文件么？**显然不应该这样。对磁盘上随机读写操作是非常耗时的，每次修改一行数据就改磁盘文件，MySQL 的执行效率会非常低下。

MySQL 为了避免频繁对磁盘做随机读写操作，引入了一个内存组件 Buffer Pool。当我们执行`update user set name='zhangsan' where id = 11;`的时候，它会先把数据从磁盘加载到 Buffer Pool 中，再对 Buffer Pool 中的数据进行修改。

![图片](https://mmbiz.qpic.cn/mmbiz_png/9YwUokWE01ywrJ1KlwRDUuKLJWzTNzTyCjVhy4lu4p71mIXqm1IQib33l0MJ1UKmgn0MIibx9GleQKY1g4jFaavA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1 "image.png")

# undo log：为什么事务能回滚？

事务是可以回滚和提交的，对应 rollback 和 commit 操作。**那事务是怎么回滚的呢？**

执行`update user set name='zhangsan' where id = 11;`操作，现在 id = 11 的这条数据已经被加载到 Buffer Pool 里面了，比如说此时 name = lisi。执行 update 操作就会修改 Buffer Pool 里的数据把它修改成 zhangsan。

什么叫回滚，回滚就是要把它改成最开始的值。我把 Buffer Pool 中的数据从 lisi 改成了 zhangsan，现在要回滚成 lisi 要怎么办？Buffer Pool 中的数据已经被覆盖了，那是不是只能从磁盘文件中，再把 id = 11 这条数据读出来？

这个时候 undo log 就派上用场了。MySQL 会在你更新数据之前，先把这行数据写到 undo log 里面，再修改 Buffer Pool 中的数据。这样一来，需要回滚就可以从 undo log 里面记录的旧值给读出来了。

![图片](https://mmbiz.qpic.cn/mmbiz_png/9YwUokWE01ywrJ1KlwRDUuKLJWzTNzTyRfGFgm1f3xadlIQFbia6DQhdd5CbAERYraEyVpqSsmy8Iicibsx2QMUMQ/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1 "image.png")

# 更新 Buffer Pool 会产生脏数据

把 Buffer Pool 中旧的数据写到 undo log 之后，就具有回滚的能力。接着就要执行 update 操作了，就是把 Buffer Pool 中的数据改成 zhangsan。改了 Buffer Pool 中的数据，这里的数据就变成了**脏数据**。

什么叫脏数据呢？MySQL 中的数据最终是要落在磁盘中的，Buffer Pool 是一个内存组件，其中的数据都是在内存中的。当我们修改 Buffer Pool 的数据之后，Buffer Pool 里的数据，就和磁盘文件中的数据不一样了。和磁盘文件中不一样的数据，就被叫做脏数据。

![图片](https://mmbiz.qpic.cn/mmbiz_png/9YwUokWE01ywrJ1KlwRDUuKLJWzTNzTyPNpeYILAmG97VrKo8dabal6PaZPd12qkJjicBW6R7bjWWeIEFq8iccqA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1 "image.png")

# redo log：回滚日志，宕机也不怕数据丢失

到目前为止，整个 update 操作都是围绕 Buffer Pool 来进行的，更新之后的数据也没有写入到磁盘中。这个时候 MySQL 宕机会发生什么？宕机之后内存中的数据就会丢失！我们刚刚更新完的数据就这么丢了，这个肯定是不允许的。

redo log 是 InnoDB 提供的另一种日志，对 Buffer Pool 中的数据执行完更新操作之后，要把这个更新操作写入到 redo log buffer 中。

![图片](https://mmbiz.qpic.cn/mmbiz_png/9YwUokWE01ywrJ1KlwRDUuKLJWzTNzTyuMXUMiahfibdQAjRWdquWPjQD8bCicbU46NLLiaccOw0vicpnb77aL2xUFw/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1 "image.png")

redo log Buffer 其实也在内存中，还需要进一步将其写入到 redo log 日志中。redo log Buffer 将日志刷入到 redo log 有这样 3 种策略，用 **innodb_flush_log_at_trx_commit** **这个参数来控制：**

* 0 提交事务，不会将 redo log buffer 的数据输入磁盘
* 1 提交事务，保证会将 redo log buffer 的数据输入磁盘
* 2 提交事务，会将 redo log buffer 的数据先输入到 os cache

这里建议将参数设置为 1，它表示我只要把日志写到 redo log Buffer，它就立即会把日志写入到 redo log。

![图片](https://mmbiz.qpic.cn/mmbiz_png/9YwUokWE01ywrJ1KlwRDUuKLJWzTNzTypsiaOgia02v531EAdQSOIf2DJvCAKOwMoaibUDLBCHicHhq32HJN0AshWg/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1 "image.png")

为什么要这么做呢？很简单，我保证把更新操作写到 redo log 日志里面，当 MySQL 宕机的时候，内存中的数据丢了，但我任然保有 redo log，可以通过 redo log 日志来恢复数据，

# bin log：归档日志

前面讲了 redo log 重做日志，它是偏物理性质的日志，InnoDB 特有的日志。这里要讲 bin log 归档日志，偏逻辑性质，是 MySQL Server 的日志。在提交事务的时候，不仅会写 redo log，还会写 bin log。

![图片](https://mmbiz.qpic.cn/mmbiz_png/9YwUokWE01ywrJ1KlwRDUuKLJWzTNzTy9rfrw6ljpiaCVian4HsJ0Xm4Q6rExAEjgX0Ib6sqMW00eUcFcYDC0aiaQ/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1 "image.png")

写 bin log 和 redo log Buffer 刷盘一样，有两个参数，通过 **sync_binlog** 来配置：

* 0 提交事务，先将 bin log 数据写入到 os cache 中，由 os cache 机制自己刷盘
* 1 提交事务，保证将 bin log 数据输入到 bin log 中

写入 redo log 和 bin log 之后，会将本次更新对应的 binlog 文件名称和地址，都写入到 redo log 中，同时在 redo log 里面加上一个 commit 标记。**这样一个事务才算是真正提交了。**

![图片](https://mmbiz.qpic.cn/mmbiz_png/9YwUokWE01ywrJ1KlwRDUuKLJWzTNzTyINaUvSx4icbLHI02GolbicU3cJFKxn03o0olSyFUGkmF8WH2HC7E4vlg/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1 "image.png")

# 异步刷盘

现在有了 undo log 可以回滚，写入了 redo log、bin log 提交事务，提交了事务之后，即使 MySQL 宕机数据也不会丢失。最后还有一个问题没有解决，之前提到的 Buffer Pool 中的脏数据要怎么处理？

实际上 MySQL 会开启一个后台线程，线程会在 MySQL 空闲的时候，不断读取 Buffer Pool 中的脏数据刷回到磁盘中。

![图片](https://mmbiz.qpic.cn/mmbiz_png/9YwUokWE01ywrJ1KlwRDUuKLJWzTNzTy3uCM5KG2l1NEiaP1cQLA59w2cYYxj0MibJK8UdFeQ1js9w6M66bv1gxQ/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1 "image.png")

# 

# 思考题：刷盘的时候怎么知道哪些是脏数据？

本文用一个`update user set name='zhangsan' where id = 11;`语句介绍了 InnoDB 的底层架构：

1. 首先会将磁盘中的数据加载到 Buffer Pool
2. 为了能够提高回滚的效率，会将旧的数据记录在 undo log 中
3. 事务提交的时候要写入到 redo log、bin log，这样即使 MySQL 宕机也能恢复数据
4. 更新 Buffer Pool 之后会产生脏数据，后台线程会在 MySQL 空闲的时候将数据刷回到磁盘中

最后提一个思考题：怎么知道 Buffer Pool 中的数据就是脏数据了？Buffer Pool 中的脏数据这么多，哪些应该先输入到磁盘中呢？Buffer Pool 满了又该怎么办？

如果你不知道怎么回答这些问题，也不打紧。这些关于 Buffer Pool 的问题，会在后面详细介绍 Buffer Pool 的时候把坑填上。
