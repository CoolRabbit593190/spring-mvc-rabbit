package com.rabbit.java.domain.dto;

import java.io.Serializable;

public class LifePayRecordDTO implements Serializable {

    private Double powerCost;
    private Double waterCost;
    private int recordMonth;
    private String lastModifyDate;
    private String housemasterName;

    public Double getPowerCost() {
        return powerCost;
    }

    public void setPowerCost(Double powerCost) {
        this.powerCost = powerCost;
    }

    public Double getWaterCost() {
        return waterCost;
    }

    public void setWaterCost(Double waterCost) {
        this.waterCost = waterCost;
    }

    public int getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(int recordMonth) {
        this.recordMonth = recordMonth;
    }

    public String getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(String lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getHousemasterName() {
        return housemasterName;
    }

    public void setHousemasterName(String housemasterName) {
        this.housemasterName = housemasterName;
    }
}
