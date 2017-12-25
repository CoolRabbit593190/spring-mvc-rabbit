package com.rabbit.java.domain.dto;

import java.io.Serializable;

public class LifePayBillDTO implements Serializable {

    private Double powerBill;
    private Double powerRate;
    private Double waterBill;
    private Double waterRate;
    private int recordMonth;
    private String lastModifyDate;

    public Double getPowerBill() {
        return powerBill;
    }

    public void setPowerBill(Double powerBill) {
        this.powerBill = powerBill;
    }

    public Double getPowerRate() {
        return powerRate;
    }

    public void setPowerRate(Double powerRate) {
        this.powerRate = powerRate;
    }

    public Double getWaterBill() {
        return waterBill;
    }

    public void setWaterBill(Double waterBill) {
        this.waterBill = waterBill;
    }

    public Double getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(Double waterRate) {
        this.waterRate = waterRate;
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
}
