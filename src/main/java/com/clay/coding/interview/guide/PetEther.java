package com.clay.coding.interview.guide;

public class PetEther {

    private Pet pet;

    private String petType;

    private Long createTime;

    public PetEther(Pet pet, String petType, Long createTime) {
        this.pet = pet;
        this.petType = petType;
        this.createTime = createTime;
    }

    public Pet getPet() {
        return this.pet;
    }

    public String getPetType() {
        return this.petType;
    }

    public Long getCreateTime() {
        return this.createTime;
    }
}
