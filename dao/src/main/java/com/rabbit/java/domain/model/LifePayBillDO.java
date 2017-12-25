package com.rabbit.java.domain.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.Date;

@TableName("lifepay_bill")
public class LifePayBillDO extends Model<LifePayBillDO> implements Serializable {
    /**
     * 如果主键id字段名为id，可不加注解
     */
    @TableId
    private Integer billId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastModifyDate;

    private Double powerBill;

    private Double powerRate;

    private Integer recordMonth;

    private Double waterBill;

    private Double waterRate;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

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

    public Integer getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(Integer recordMonth) {
        this.recordMonth = recordMonth;
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

    @Override
    protected Serializable pkVal() {
        return billId;
    }
}