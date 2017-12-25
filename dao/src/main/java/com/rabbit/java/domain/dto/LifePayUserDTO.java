package com.rabbit.java.domain.dto;

import java.io.Serializable;

public class LifePayUserDTO implements Serializable {
    private Integer familyId;

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }
}
