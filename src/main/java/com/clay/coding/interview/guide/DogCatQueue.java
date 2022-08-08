package com.clay.coding.interview.guide;

import java.util.Queue;

/**
 * @author yuntzhao
 */
public class DogCatQueue {

    private Queue<PetEther> catQueue;

    private Queue<PetEther> dogQueue;

    private Long count;

    public DogCatQueue() {

    }

    public void add(Pet pet, String petType) {
        if (petType.equals("dog")) {
            this.dogQueue.add(new PetEther(pet, petType, System.currentTimeMillis()));
        }

        if (petType.equals("cat")) {
            this.catQueue.add(new PetEther(pet, petType, System.currentTimeMillis()));
        }

        count++;
    }

    public Pet.Dog pollDog() throws Exception {
        if (!dogQueue.isEmpty()) {
            count--;
            return (Pet.Dog) dogQueue.poll().getPet();
        }
        throw new Exception();
    }

    public Pet.Cat pollCat() throws Exception {
        if (!catQueue.isEmpty()) {
            count--;
            return (Pet.Cat) catQueue.poll().getPet();
        }
        throw new Exception();
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isDogEmpty() {
        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }

    public Pet pollAll() throws Exception {
        if (dogQueue.isEmpty() && catQueue.isEmpty()) {
            throw new Exception();
        } else if (dogQueue.isEmpty()) {
            return catQueue.poll().getPet();
        } else if (catQueue.isEmpty()) {
            return dogQueue.poll().getPet();
        } else {
            if (dogQueue.peek().getCreateTime() > catQueue.peek().getCreateTime()) {
                return dogQueue.poll().getPet();
            } else {
                return catQueue.poll().getPet();
            }
        }
    }
}
