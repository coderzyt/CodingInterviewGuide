# JMM

## CPU缓存模型

CPU缓存则是为了解决CPU处理速度和内存处理速度不对等的问题。
CPU Cache 缓存的是内存数据用于解决CPU处理速度和内存不匹配的问题，内存缓存的是硬盘数据用
于解决硬盘访问速度过慢的问题。
![](C:/Users/ADMINI~1/AppData/Local/Temp/cpu-cache.45d4b8b6.jpg)
CPU为了解决内存缓存不一致的问题可以通过指定缓存一致性协议或者其他手段来解决。

## 指令重排序

简单来说就是系统在执行代码的时候并不一定是按照你写的代码顺序依次执行。
通常有两种情况：

1. 编译器优化重排序：编译器在不改变单线程程序语义的前提下，重新安排语句的执行顺序。
2. 指令并行重排：现代处理器采用了指令级并行技术来将多条指令重叠执行。如果不存在数据依赖
   性， 进而导致程序在多线程下执行可能出现问题。

Java源代码会经历 编译器优化重排 - 指令并行重排 - 内存系统重排 的过程，最终才变成操作系统
可执行的指令序列。
指令重排序可以保证串行语义一致，但是没有义务保证多线程间的语义也一致，所以在多线程下，指令
重排序可能会导致一些问题。

## JMM Java Memory Model

Java内存模型抽象了线程和主内存间之的关系，就比如线程之间的共享变量必须存在主内存中。

1. 主内存：所有线程创建的实例对象都存放在主内存中，不管该实例对象是成员变量还是方法中的本
   地变量（也成为局部变量 ）
2. 本地内存：每个线程都有一个私有的本地内存来存储共享变量的副本，并且每个线程只能访问自己
   的本地内存，无法访问其他线程的本地内存。本地内存是JMM抽象出来的一个概念，存储了主内存中的
   共享变量副本。

Java内存模型的抽象示意图如下：
![](C:/Users/ADMINI~1/AppData/Local/Temp/jmm.508a777f.png)

## 并发编程三个重要特性

### 原子性

一次操作或者多次操作，要么所有的操作全部都得到执行并且不会受到任何因素的干扰而中断，要么
都不执行。

### 可见性

当一个线程对共享变量进行了修改，那么另外的线程都是立即可以看到修改后的最新值。

### 有序性

由于指令重排序问题，代码的执行顺序未必就是编写代码时候的顺序。
在Java中，volatile关键字可以禁止指令进行重排序优化。

## volatile关键字

### 如何保证变量的可见性

volatile关键字可以保证变量的可见性，如果我们将变量声明为volatile，这就指示JVM，这个
变量是共享且不稳定的，每次使用它都到主内存中进行读取。
volatile关键字能保证数据的可见性，但不能保证数据的原子性。synchronized关键字两者都能
保证。

### 如何禁止指令重排序

在Java中，volatile关键字除了可以保证变量的可见性，还有一个重要的作用就是防止JVM的指令重
排序。如果我们将变量声明为volatile，在对这个变量进行读写操作的时候，会通过插入特定的
***内存屏障***的方式来禁止指令重排序。

```
public native void loadFence();
public native void storeFence();
public native void fullFence();
```

双重校验锁实现对象单例（线程安全）：

```java
public class Singleton {
    private volatile static Singleton uniqueInstance;
    private Singleton() {}
    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
```

uniqueInstance 采用volatile关键字修饰也是很有必要的，uniqueInstance = new Singleton();

这段代码其实是分为三步执行：

1. 为uniqueInstance分配内存空间
2. 初始化uniqueInstance
3. 将uniqueInstance指向分配的内存地址

## synchronized关键字

说一说自己对于synchronized关键字的了解

主要解决的是多个线程之间访问资源的同步性，可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行。

jdk1.6对锁的实现引入了大量的优化，如自旋锁，适应性自旋锁，锁消除，锁粗化，偏向锁，轻量级锁等技术来减少

锁操作的开销。

### 如何使用synchronized关键字

1. 修饰实例方法
2. 修饰静态方法
3. 修饰代码块

#### 修饰实例方法（锁当前对象实例）

给当前的对象加锁，进入同步代码前要获得 **当前对象实例的锁**

```java
synchronized void method() {
    // 业务代码
}
```

#### 修饰静态方法（锁当前类）

给当前类加锁，会作用于类的所有对象实例，进入同步代码前要获得当前class的锁。这是因为静态成员不属于任何一个

实例对象，归整个类所有，不依赖于类的特定实例，被类的所有实例共享。

```java
synchronized static void method() {
    // 业务代码
}
```

#### 修饰代码块（锁指定对象/类）

对括号里的对象/类加锁：

* `synchronized(Object)`表示进入同步代码块前要获得**给定对象的锁**
* `synchronized(类.class)`表示进入同步代码块前要获得**给定class的锁**

```java
synchronized(this) {
    // 业务代码
}
```

总结

* synchronized关键字加到static静态方法和synchronized(class)代码块上都是给Class类加锁；
* synchronized关键字加到实例方法上是给对象实例上锁；
* 尽量不用使用synchronized(String a)因为JVM中，字符串常量池具有缓存功能。

### synchronized关键字底层原理

synchronized同步语句块的情况

```java
public class SynchronizedDemo() {
    public void method() {
        synchronized(this) {
            System.out.println("synchronized 代码块");
        }
    }
}
```

```
 Last modified 2022年8月11日; size 558 bytes
  SHA-256 checksum ee5d89d540e03fc22866de6ec30a95c9ac60ec83fbc700bc06bf502f29562f84
  Compiled from "SynchronizedDemo.java"
public class com.clay.coding.java.guide.SynchronizedDemo
  minor version: 0
  major version: 61
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #21                         // com/clay/coding/java/guide/SynchronizedDemo
  super_class: #2                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 2, attributes: 1
Constant pool:
   #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
   #2 = Class              #4             // java/lang/Object
   #3 = NameAndType        #5:#6          // "<init>":()V
   #4 = Utf8               java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Fieldref           #8.#9          // java/lang/System.out:Ljava/io/PrintStream;
   #8 = Class              #10            // java/lang/System
   #9 = NameAndType        #11:#12        // out:Ljava/io/PrintStream;
  #10 = Utf8               java/lang/System
  #11 = Utf8               out
  #12 = Utf8               Ljava/io/PrintStream;
  #13 = String             #14            // synchronized 代码块
  #14 = Utf8               synchronized 代码块
  #15 = Methodref          #16.#17        // java/io/PrintStream.println:(Ljava/lang/String;)V
  #16 = Class              #18            // java/io/PrintStream
  #17 = NameAndType        #19:#20        // println:(Ljava/lang/String;)V
  #18 = Utf8               java/io/PrintStream
  #19 = Utf8               println
  #20 = Utf8               (Ljava/lang/String;)V
  #21 = Class              #22            // com/clay/coding/java/guide/SynchronizedDemo
  #22 = Utf8               com/clay/coding/java/guide/SynchronizedDemo
  #23 = Utf8               Code
  #24 = Utf8               LineNumberTable
  #25 = Utf8               method
  #26 = Utf8               StackMapTable
  #27 = Class              #28            // java/lang/Throwable
  #28 = Utf8               java/lang/Throwable
  #29 = Utf8               SourceFile
  #30 = Utf8               SynchronizedDemo.java
{
  public com.clay.coding.java.guide.SynchronizedDemo();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 6: 0

  public void method();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter
         4: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
         7: ldc           #13                 // String synchronized 代码块
         9: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        12: aload_1
        13: monitorexit
        14: goto          22
        17: astore_2
        18: aload_1
        19: monitorexit
        20: aload_2
        21: athrow
        22: return
      Exception table:
         from    to  target type
             4    14    17   any
            17    20    17   any
      LineNumberTable:
        line 8: 0
        line 9: 4
        line 10: 12
        line 11: 22
      StackMapTable: number_of_entries = 2
        frame_type = 255 /* full_frame */
          offset_delta = 17
          locals = [ class com/clay/coding/java/guide/SynchronizedDemo, class java/lang/Object ]
          stack = [ class java/lang/Throwable ]
        frame_type = 250 /* chop */
          offset_delta = 4
}
SourceFile: "SynchronizedDemo.java"

```

从上面我们可以看出：**`synchronized` 同步语句块的实现使用的是 `monitorenter` 和 `monitorexit` 指令，其中 `monitorenter` 指令指向同步代码块的开始位置，`monitorexit` 指令则指明同步代码块的结束位置。**

当执行 `monitorenter` 指令时，线程试图获取锁也就是获取 **对象监视器 `monitor`** 的持有权。

> 在 Java 虚拟机(HotSpot)中，Monitor 是基于 C++实现的，由[ObjectMonitoropen in new window](https://github.com/openjdk-mirror/jdk7u-hotspot/blob/50bdefc3afe944ca74c3093e7448d6b889cd20d1/src/share/vm/runtime/objectMonitor.cpp)实现的。每个对象中都内置了一个 `ObjectMonitor`对象。
>
> 另外，`wait/notify`等方法也依赖于 `monitor`对象，这就是为什么只有在同步的块或者方法中才能调用 `wait/notify`等方法，否则会抛出 `java.lang.IllegalMonitorStateException`的异常的原因。

在执行 `monitorenter`时，会尝试获取对象的锁，如果锁的计数器为 0 则表示锁可以被获取，获取后将锁计数器设为 1 也就是加 1。

![执行 monitorenter 获取锁](http://javaguide.cn/assets/synchronized-get-lock-code-block.eb4a133a.jpg)

对象锁的的拥有者线程才可以执行 `monitorexit` 指令来释放锁。在执行 `monitorexit` 指令后，将锁计数器设为 0，表明锁被释放，其他线程可以尝试获取锁。

![执行 monitorexit 释放锁](http://javaguide.cn/assets/synchronized-release-lock-block.a9a3d034.jpg)

如果获取对象锁失败，那当前线程就要阻塞等待，直到锁被另外一个线程释放为止。

#### synchronized 修饰方法的的情况

```java
public class SynchronizedDemo2 {
    public synchronized void method() {
        System.out.println("synchronized 方法");
    }
}

```

![synchronized关键字原理](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019-6/synchronized%E5%85%B3%E9%94%AE%E5%AD%97%E5%8E%9F%E7%90%862.png)

`synchronized` 修饰的方法并没有 `monitorenter` 指令和 `monitorexit` 指令，取得代之的确实是 `ACC_SYNCHRONIZED` 标识，该标识指明了该方法是一个同步方法。JVM 通过该 `ACC_SYNCHRONIZED` 访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。

如果是实例方法，JVM 会尝试获取实例对象的锁。如果是静态方法，JVM 会尝试获取当前 class 的锁。

#### 总结

`synchronized` 同步语句块的实现使用的是 monitorenter 和 monitorexit 指令，其中 monitorenter 指令指向同步代码块的开始位置，monitorexit 指令则指明同步

`synchronized` 修饰的方法并没有 `monitorenter` 指令和 `monitorexit` 指令，取得代之的确实是 `ACC_SYNCHRONIZED` 标识，该标识指明了该方法是一个同步方法。

不过两者的本质都是对 **对象监视器 monitor** 的获取

### JDK1.6 之后的 synchronized 关键字底层做了哪些优化？

JDK1.6 对锁的实现引入了大量的优化，如偏向锁、轻量级锁、自旋锁、适应性自旋锁、锁消除、锁粗化等技术来减少锁操作的开销。

锁主要存在四种状态，依次是：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态，他们会随着竞争的激烈而逐渐升级。注意锁可以升级不可降级，这种策略是为了提高获得锁和释放锁的效率。

关于这几种优化的详细信息可以查看下面这篇文章：[Java6 及以上版本对 synchronized 的优化](https://www.cnblogs.com/wuqinglong/p/9945618.html)

#### Java对象头（存储锁类型）

在HotSpot虚拟机中，对象在内存中的布局分为三块区域：对象头，实例数据和对齐填充。

对象头中包含两部分：MarkWord和类型指针。如果是数组对象的话，对象头还有一部分是存储数组的长度。

多线程下synchronized的加锁就是对同一个对象的对象头中的MarkWord中的变量进行CAS操作。

1. MarkWord：用于存储对象自身的运行时数据，如HashCode，GC分代年龄，锁状态标志，线程持有的锁，偏向线程ID等等。占用内存大小与虚拟机位长一致
   （32位JVM -> MarkWord是32位，64位JVM -> MarkWord是64位）
2. 类型指针：虚拟机通过这个指针确定该对象是哪个类的实例
3. 对象头的长度
   | 长度      | 内容                   | 说明                             |
   | --------- | ---------------------- | -------------------------------- |
   | 32/64 bit | MarkWord               | 存储对象的hashCode或者锁信息等   |
   | 32/64 bit | Class Metadata Address | 存储对象类型数据的指针           |
   | 32/64 bit | Array Length           | 数组的长度（如果当前对象是数组） |

如果是数组对象的话，虚拟机使用3个字宽（32/64 bit + 32/64 bit + 32/64 bit）存储对象头，如果是普通对象的话，虚拟机用2个字宽存储对象头（32/64 bit + 32/64 bit）

#### 优化后synchronized锁的分类

级别从低到高依次是：

1. 无锁状态
2. 偏向锁状态
3. 轻量级锁状态
4. 重量级锁状态

锁可以升级但不能降级。即：无锁 -> 偏向锁 -> 轻量级锁 -> 重量级锁 是单向的。

下面看每个锁状态时，对象头中的MarkWord这一字节中的内容是什么。

以32位系统为例：

1. 无锁状态

   | 25bit          | 4bit         | 1bit（是否是偏向锁） | 2bit（锁标志位） |
   | -------------- | ------------ | -------------------- | ---------------- |
   | 对象的hashCode | 对象分代年龄 | 0                    | 01               |

   这里的hashCode是Object#hashCode 或者 System#identityHashCode计算出来的值，不是用户覆盖产生的hashCode。
2. 偏向锁状态

   | 23bit  | 2bit  | 4bit         | 1bit | 2bit |
   | ------ | ----- | ------------ | ---- | ---- |
   | 线程ID | epoch | 对象分代年龄 | 1    | 01   |

   这里的线程ID和epoch占用了hashCode的位置，所以，如果对象计算过identityHashCode后，便无法进入偏向锁状态，反过来，如果对象处于偏向锁状态，并且需要计算其identityHashCode的话，则偏向锁会被撤销，升级为重量级锁。

   epoch：对于偏向锁，如果线程ID=0表示未加锁。

   什么时候会计算HashCode呢？比如：将对象作为Map的Key时会自动触发计算，List就不会计算，日常创建一个对象，持久化到库里，进行json序列化，或者作为临时对象等，这些情况下，并不会触发计算hashCode，所以大部分情况不会计算hashCode。

   IdentityHashCode是未被覆写的java.lang.Object.hashCode()或者java.lang.System.IdentityHashCode(Obejct)所返回的值。
3. 轻量级锁状态

   | 30bit                  | 2bit |
   | ---------------------- | ---- |
   | 指向线程栈帧记录的指针 | 00   |

   这里指向栈帧中的Lock Record记录，里面当然可以记录对象的IdentityHashCode。
4. 重量级锁状态

   | 30bit              | 2bit |
   | ------------------ | ---- |
   | 指向锁监视器的指针 | 10   |

   这里指向了内存中对象的ObjectMonitor对象，而ObjectMonitor对象可以存储对象的IdentityHashCode的值。

#### 锁的升级

##### 1. 偏向锁

偏向锁是针对于一个线程而言的，线程获得锁之后就不会再有解锁等操作了，这样可以省略很多开销。假如有两个线程来竞争该锁的话，那么偏向锁就失效了，进而升级成轻量级锁了。
如果支持偏向锁（没有计算hashCode），那么在分配对象时，分配一个可偏向而未偏向的对象（MarkWord的最后3位为101，并且Thread Id字段的值为0）。

1. 偏向锁的加锁

   1. 偏向锁标志是未偏向状态，使用 CAS 将 MarkWord 中的线程ID设置为自己的线程ID，
      1. 如果成功，则获取偏向锁成功。
      2. 如果失败，则进行锁升级。
   2. 偏向锁标志是已偏向状态
      1. MarkWord 中的线程 ID 是自己的线程 ID，成功获取锁
      2. MarkWord 中的线程 ID 不是自己的线程 ID，需要进行锁升级

   偏向锁的锁升级需要进行偏向锁的撤销。
2. 偏向锁的撤销

   1. 对象是不可偏向的状态
      1. 不需要撤销
   2. 对象是可偏向状态
      1. MarkWord中指向的线程不存活
         1. 允许重偏向：退回到可偏向但未偏向的状态
         2. 不允许重偏向：变成无锁状态
      2. MarkWord中指向的线程存活
         1. 线程ID指向的线程仍然拥有锁
            1. 升级为轻量级锁，将MarkWord复制到线程栈中
         2. 不再拥有锁
            1. 允许重偏向：退回到可偏向但未偏向的状态
            2. 不允许重偏向：变成无锁状态

**小结：** 撤销偏向的操作需要在全局检查点执行。我们假设线程A曾经拥有锁（不确定是否释放锁）， 线程B来竞争锁对象，如果当线程A不在拥有锁时或者死亡时，线程B直接去尝试获得锁（根据是否 允许重偏向（`rebiasing`），获得偏向锁或者轻量级锁）；如果线程A仍然拥有锁，那么锁 升级为轻量级锁，线程B自旋请求获得锁。

偏向锁的撤销流程

![](https://img2020.cnblogs.com/blog/883454/202111/883454-20211126170433124-96869961.png)

##### 2. 轻量级锁

之所以是轻量级锁，是因为它仅仅使用CAS进行操作，实现获取锁。

1. 加锁流程：如果流程发现对象头中MarkWord已经存在指向自己栈帧的指针，即线程已经获得轻量级锁，那么只需要将0存储在自己的栈帧中（此过程称为递归加锁）；在解锁的时候，如果发现锁记录的内容为0，那么只需要移除栈帧中的锁记录即可，而不需要更新MarkWord。

加锁前：

![](https://img2020.cnblogs.com/blog/883454/202111/883454-20211126170417197-588115916.png)

加锁后：

![](https://img2020.cnblogs.com/blog/883454/202111/883454-20211126170446401-297347615.png)

线程尝试使用CAS将对象头中的MarkWord替换为指向锁记录（Lock Record）的指针，如上图所示。如果成功，当前线程获得轻量级锁，如果失败，虚拟机先检查当前对象头的MarkWord是否指向当前线程的栈帧，如果指向，则说明当前线程已经拥有这个对象的锁，则可以直接进入同步块执行操作，否则表示蛋其他线程竞争锁，当前线程便尝试使用自旋锁来获取锁。当竞争线程的自旋次数达到界限值（threshold），轻量级锁将会膨胀为重量级锁。

2. 撤销流程：轻量级锁解锁时，如果对象的MarkWord仍然指向着线程的锁记录，会使用CAS操作，将Displaced MarkWord替换到对象头，如果成功，则表示没有竞争发生。如果失败，表示当前锁存在锁竞争，锁就会膨胀为重量级锁。

##### 3. 重量级锁

重量级锁，是使用操作系统互斥量（mutex）来实现的传统锁。当所有对锁的优化都失效时，将退回到重量级锁。它与轻量级锁不同竞争的线程不再通过自旋来竞争线程，而是直接进入堵塞状态，此时不消耗CPU，然后等用有所的线程释放锁后，唤醒堵塞的线程，然后线程再次竞争锁。但是注意，当锁膨胀为重量级锁时，就不能再退回到轻量级锁。

### synchronized和volatile的区别

两者时互补的存在，不是对立的存在

* `volatile` 关键字是线程同步的轻量级实现，所以 `volatile`性能肯定比 `synchronized`关键字要好 。但是 `volatile` 关键字只能用于变量而 `synchronized` 关键字可以修饰方法以及代码块 。
* `volatile` 关键字能保证数据的可见性，但不能保证数据的原子性。`synchronized` 关键字两者都能保证。
* `volatile`关键字主要用于解决变量在多个线程之间的可见性，而 `synchronized` 关键字解决的是多个线程之间访问资源的同步性。

### synchronized 和 ReentrantLock 的区别

#### 两者都是可重入锁

`synchronized` 是依赖于 JVM 实现的，前面我们也讲到了 虚拟机团队在 JDK1.6 为 `synchronized` 关键字进行了很多优化，但是这些优化都是在虚拟机层面实现的，并没有直接暴露给我们。`ReentrantLock` 是 JDK 层面实现的（也就是 API 层面，需要 lock() 和 unlock() 方法配合 try/finally 语句块来完成），所以我们可以通过查看它的源代码，来看它是如何实现的。

#### ReentrantLock 比 synchronized 增加了一些高级功能

相比 `synchronized`，`ReentrantLock`增加了一些高级功能。主要来说主要有三点：

* **等待可中断** : `ReentrantLock`提供了一种能够中断等待锁的线程的机制，通过 `lock.lockInterruptibly()` 来实现这个机制。也就是说正在等待的线程可以选择放弃等待，改为处理其他事情。
* **可实现公平锁** : `ReentrantLock`可以指定是公平锁还是非公平锁。而 `synchronized`只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。`ReentrantLock`默认情况是非公平的，可以通过 `ReentrantLock`类的 `ReentrantLock(boolean fair)`构造方法来制定是否是公平的。
* **可实现选择性通知（锁可以绑定多个条件）** : `synchronized`关键字与 `wait()`和 `notify()`/`notifyAll()`方法相结合可以实现等待/通知机制。`ReentrantLock`类当然也可以实现，但是需要借助于 `Condition`接口与 `newCondition()`方法。

### ThreadLocal

#### ThreadLocal有什么用

**`ThreadLocal`类主要解决的就是让每个线程绑定自己的值，可以将 `ThreadLocal`类形象的比喻成存放数据的盒子，盒子中可以存储每个线程的私有数据。**

如果你创建了一个 `ThreadLocal`变量，那么访问这个变量的每个线程都会有这个变量的本地副本，这也是 `ThreadLocal`变量名的由来。他们可以使用 `get（）` 和 `set（）` 方法来获取默认值或将其值更改为当前线程所存的副本的值，从而避免了线程安全问题。

#### 如何使用 ThreadLocal？

```
public class ThreadLocalExample implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat(
            "yyyyMMdd HHmm"
    ));

    @Override
    public void run() {
        System.out.println("Thread Name = " + Thread.currentThread().getName() + " default formatter = " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name = " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample obj = new ThreadLocalExample();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }
}
```

#### ThreadLocal 原理了解吗？

**最终的变量是放在了当前线程的 `ThreadLocalMap` 中，并不是存在 `ThreadLocal` 上，`ThreadLocal` 可以理解为只是 `ThreadLocalMap`的封装，传递了变量值。** `ThrealLocal` 类中可以通过 `Thread.currentThread()`获取到当前线程对象后，直接通过 `getMap(Thread t)`可以访问到该线程的 `ThreadLocalMap`对象。

**每个 `Thread`中都具备一个 `ThreadLocalMap`，而 `ThreadLocalMap`可以存储以 `ThreadLocal`为 key ，Object 对象为 value 的键值对。**

```
ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
    //......
}
```

比如我们在同一个线程中声明了两个 `ThreadLocal` 对象的话， `Thread`内部都是使用仅有的那个 `ThreadLocalMap` 存放数据的，`ThreadLocalMap`的 key 就是 `ThreadLocal`对象，value 就是 `ThreadLocal` 对象调用 `set`方法设置的值。

![threadlocal-data-structure](http://javaguide.cn/assets/threadlocal-data-structure.aa76aca6.jpg)

`ThreadLocalMap`是 `ThreadLocal`的静态内部类。

![ThreadLocal内部类](http://javaguide.cn/assets/thread-local-inner-class.fc4bb676.png)

#### ThreadLocal 内存泄露问题是怎么导致的？

`ThreadLocalMap` 中使用的 key 为 `ThreadLocal` 的弱引用，而 value 是强引用。所以，如果 `ThreadLocal` 没有被外部强引用的情况下，在垃圾回收的时候，key 会被清理掉，而 value 不会被清理掉。

这样一来，`ThreadLocalMap` 中就会出现 key 为 null 的 Entry。假如我们不做任何措施的话，value 永远无法被 GC 回收，这个时候就可能会产生内存泄露。`ThreadLocalMap` 实现中已经考虑了这种情况，在调用 `set()`、`get()`、`remove()` 方法的时候，会清理掉 key 为 null 的记录。使用完 `ThreadLocal`方法后 最好手动调用 `remove()`方法。

```
static class Entry extends WeakReference<ThreadLocal<?>> {
    /** The value associated with this ThreadLocal. */
    Object value;

    Entry(ThreadLocal<?> k, Object v) {
        super(k);
        value = v;
    }
}
```

**弱引用介绍：**

> 如果一个对象只具有弱引用，那就类似于 **可有可无的生活用品** 。弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它 所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程， 因此不一定会很快发现那些只具有弱引用的对象。
>
> 弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，Java 虚拟机就会把这个弱引用加入到与之关联的引用队列中。

# 线程池

线程池可以 降低资源消耗，提高响应速度，提高线程的可管理性

### excute() 和 submit()方法的区别

1. **`execute()`方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功与否；**
2. **`submit()`方法用于提交需要返回值的任务。线程池会返回一个 `Future` 类型的对象，通过这个 `Future` 对象可以判断任务是否执行****成功** ，并且可以通过 `Future` 的 `get()`方法来获取返回值，`get()`方法会阻塞当前线程直到任务完成，而使用 `get(long timeout，TimeUnit unit)`方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完。

![图解线程池实现原理](http://javaguide.cn/assets/%E5%9B%BE%E8%A7%A3%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%AE%9E%E7%8E%B0%E5%8E%9F%E7%90%86.2b9eb21a.png)

## AQS

### AQS原理分析

AQS的核心思想是，如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制AQS是用CLH队列锁实现的，即 将暂时获取不到锁的线程加入队列中。

> CLH（Craig Landin and Hagersten）队列的是一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在结点之间的关联关系）。AQS是将每条请求共享资源的线程封装成一个CLH锁队列的一个节点（Node）来实现锁的分配。

看个AQS（AbstractQueuedSynchronizer）原理图：

![AQS原理图](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019-6/AQS%E5%8E%9F%E7%90%86%E5%9B%BE.png)

AQS使用一个int成员变量来表示同步状态，通过内置的FIFO队列来完成获取资源线程的排队工作。AQS使用CAS对该同步状态进行原子操作实现对其值的修改。

```
// 共享变量，使用volatile修饰保证线程可见性
private volatile int state;
```

状态信息通过protected类型的getState，setState，compareAndSetState进行操作。

```
// 返回同步状态的当前值 
protected final int getState() {
    return state;
}

// 设置同步状态的值
protected final void setState(int newState) {
    state = newState;
}

// 原子地（CAS操作）将同步状态值设置为给定值update如果当前同步状态的值等于expect（期望值）
protected final boolean compareAndSetState(int expect, int update) {
    return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
}
```

### AQS对资源的共享方式

#### AQS定义两种资源共享方式

* Exclusive（独占）：只有一个线程能执行，如 `ReentrantLock`。又可分为公平锁和非公平锁：
  * 公平锁：按照线程在队列中的排队顺序，先到者先拿到锁
  * 非公平锁：当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的
* Share（共享）：多个线程可同时执行，如 `CountDownLatch`，`Semaphore`，`CyclicBarrier`，`ReadWriteLock`我们都会在后面讲到。

`ReentrantReadWriteLock`可以看作时组合式，因为 `ReentrantReadWriteLock`也就是读写锁允许多个线程同时对某一资源进行读。

#### AQS组件总结

* `Semaphore`(信号量）- 允许多个线程同时访问：`synchronized` 和 `ReentrantLock`都是一次只允许一个线程访问某个资源，`Semaphore`（信号量）可以指定多个线程同时访问某个资源。
* `CountDownLatch`（倒计时器）：`CountDownLatch`是一个同步工具类，用来协调多个线程之间的同步。这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，再开始执行。
* `CylicBarrier`（循环栅栏）：`CylicBarrier`和 `CountDownLatch`非常类似，它也可以实现线程间的技术等待，但是它的功能比 `CountDownLatch`更加复杂和强大。主要应用场景和 `CountDownLatch`类似。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。`CylicBarrier`默认的构造方法是 `CylicBarrier(int parties)`，其参数表示屏障拦截的线程数量，每个线程调用 `await()`方法告诉 `CylicBarrier`我已经到达了屏障，然后当前线程被阻塞。

## 线程池

#### 正确配置线程池的参数

上下文切换：

多线程编程中一般线程个数都大于CPU核心数，而一个CPU核心在任意时刻只能被一个线程使用，为了让这些线程都能得到有效执行，CPU采取的策略是为每个线程非陪时间片并轮转的形式。当一个线程的时间片用完的时候就会重新处于就绪状态让给其他线程使用，这个过程就属于一次上下文切换。概括来说就是：当前任务在执行完CPU时间片切换到另一个任务之前会先保存自己的状态，以便于下次再切换回这个任务时，可以再加载这个任务的状态。**任务从保存到再加载的过程就是一次上下文切换。**

Linux相比与其他操作系统（包括其他类Unix系统）有很多的优点，其中有一项就是，其上下文切换和模式切换的时间消耗非常少。

* CPU密集型任务（N+1）：这种任务消耗的主要是CPU资源，可以将线程数设置为N（CPU核心数）+1。比CPU核心数多出来的一个线程时为了防止线程偶发的缺页中断，或者其他原因导致的任务暂停而带来的影响。一旦任务暂停，CPU就会处于空闲状态，而在这种情况下多出来的一个线程就可以充分利用CPU的空闲时间。
* I/O密集型任务（2N）：这种任务应用起来，系统会用大部分的时间来处理I/O交互，而线程在处理I/O的时间段内不会占用CPU来处理，这时就可以将CPU交出给其他线程使用。因此在I/O密集型任务的应用中，我们可以多配置一些线程，具体计算方法是2N。

> 线程数更严谨的计算方法是：`最佳线程数 = N（CPU核心数）+（1+WT（线程等待时间）/ ST（线程计算时间））`线程等待时间所占比例越高，需要越多线程。我们可以通过JDK自带的工具VisualVM来查看 `WT/ST`比例。
>
> CPU密集型任务的 `WT/ST`接近或者等于0，因此，线程数可以设置为 `N（CPU核心数）+（1+0）= N`，和我们上面说的是 `N（CPU核心数）+ 1`差不多
>
> I/O密集型任务下，几乎全是线程等待时间，从理论上来说，你就可以将线程数设置为2N（按道理来说，WT/ST的结果应该比较大，这里选择2N的原因应该是为了避免创建过多线程吧）。



# Java内存区域详解

JDK1.8 之前

[JVM%E8%BF%90%E8%A1%8C%E6%97%B6%E6%95%B0%E6%8D%AE%E5%8C%BA%E5%9F%9F.5f095134.png](https://javaguide.cn/assets/JVM%E8%BF%90%E8%A1%8C%E6%97%B6%E6%95%B0%E6%8D%AE%E5%8C%BA%E5%9F%9F.5f095134.png)

JDK1.8

[Java%E8%BF%90%E8%A1%8C%E6%97%B6%E6%95%B0%E6%8D%AE%E5%8C%BA%E5%9F%9FJDK1.8.dbbe1f77.png](https://javaguide.cn/assets/Java%E8%BF%90%E8%A1%8C%E6%97%B6%E6%95%B0%E6%8D%AE%E5%8C%BA%E5%9F%9FJDK1.8.dbbe1f77.png)

线程私有的：

* 程序计数器
* 虚拟机栈
* 本地方法栈

线程共享的：

* 堆
* 方法区 -> 元空间
* 直接内存（非运行时数据区的一部分）

## 程序计数器

程序计数器是一块较小的内存空间，可以看作是当前线程所执行的行号指示器。字节码解释器工作时通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支，循环，跳转，异常处理，线程回复等功能都需要依赖这个计数器来完成。为了线程切换后能恢复到正确的执行位置，每条线程都需要有一个独立的程序计数器，各线程之间计数器互不影响，独立存储，我们称这类内存区域为“线程私有"的内存。

⚠️ 注意 ：程序计数器是唯一一个不会出现** **`OutOfMemoryError` 的内存区域，它的生命周期随着线程的创建而创建，随着线程的结束而死亡。
