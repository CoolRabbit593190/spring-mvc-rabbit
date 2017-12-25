package com.rabbit.java.domain.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.Date;

@TableName("lifepay_power_use_record")
public class LifePayPowerUseRecordDO extends Model<LifePayPowerUseRecordDO> implements Serializable {
    @TableId
    private Integer recordId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastModifyDate;

    private Double powerUse;

    private Integer recordMonth;

    private Integer familyId;

    private LifePayFamilyDO family;

    public LifePayFamilyDO getFamily() {
        return family;
    }

    public void setFamily(LifePayFamilyDO family) {
        this.family = family;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Double getPowerUse() {
        return powerUse;
    }

    public void setPowerUse(Double powerUse) {
        this.powerUse = powerUse;
    }

    public Integer getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(Integer recordMonth) {
        this.recordMonth = recordMonth;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Override
    protected Serializable pkVal() {
        return recordId;
    }
}