package com.clay.coding.interview.guide;

public class Pet {

    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }


    public class Cat extends Pet {

        public Cat() {
            super("cat");
        }
    }

    public class Dog extends Pet {

        public Dog() {
            super("dog");
        }
    }
}
