package com.rabbit.java.domain.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@TableName("lifepay_family")
public class LifePayFamilyDO extends Model<LifePayFamilyDO> implements Serializable {
    @TableId
    private Integer familyId;

    private String housemasterName;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastModifyDate;

    private LifePayFamilyDescDO familyDesc;

    private Set<LifePayUserDO> familyMembers;

    private Set<LifePayRecordDO> records;

    private Set<LifePayPowerUseRecordDO> powerUseRecords;

    public Set<LifePayUserDO> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(Set<LifePayUserDO> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public Set<LifePayRecordDO> getRecords() {
        return records;
    }

    public void setRecords(Set<LifePayRecordDO> records) {
        this.records = records;
    }

    public Set<LifePayPowerUseRecordDO> getPowerUseRecords() {
        return powerUseRecords;
    }

    public void setPowerUseRecords(Set<LifePayPowerUseRecordDO> powerUseRecords) {
        this.powerUseRecords = powerUseRecords;
    }

    public LifePayFamilyDescDO getFamilyDesc() {
        return familyDesc;
    }

    public void setFamilyDesc(LifePayFamilyDescDO familyDesc) {
        this.familyDesc = familyDesc;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getHousemasterName() {
        return housemasterName;
    }

    public void setHousemasterName(String housemasterName) {
        this.housemasterName = housemasterName == null ? null : housemasterName.trim();
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LifePayFamilyDO)) return false;

        LifePayFamilyDO familyDO = (LifePayFamilyDO) o;

        if (!getFamilyId().equals(familyDO.getFamilyId())) return false;
        if (getHousemasterName() != null ? !getHousemasterName().equals(familyDO.getHousemasterName()) : familyDO.getHousemasterName() != null)
            return false;
        if (getLastModifyDate() != null ? !getLastModifyDate().equals(familyDO.getLastModifyDate()) : familyDO.getLastModifyDate() != null)
            return false;
        if (getFamilyDesc() != null ? !getFamilyDesc().equals(familyDO.getFamilyDesc()) : familyDO.getFamilyDesc() != null)
            return false;
        if (getFamilyMembers() != null ? !getFamilyMembers().equals(familyDO.getFamilyMembers()) : familyDO.getFamilyMembers() != null)
            return false;
        if (getRecords() != null ? !getRecords().equals(familyDO.getRecords()) : familyDO.getRecords() != null)
            return false;
        return getPowerUseRecords() != null ? getPowerUseRecords().equals(familyDO.getPowerUseRecords()) : familyDO.getPowerUseRecords() == null;
    }

    @Override
    public int hashCode() {
        int result = getFamilyId().hashCode();
        result = 31 * result + (getHousemasterName() != null ? getHousemasterName().hashCode() : 0);
        result = 31 * result + (getLastModifyDate() != null ? getLastModifyDate().hashCode() : 0);
        result = 31 * result + (getFamilyDesc() != null ? getFamilyDesc().hashCode() : 0);
        result = 31 * result + (getFamilyMembers() != null ? getFamilyMembers().hashCode() : 0);
        result = 31 * result + (getRecords() != null ? getRecords().hashCode() : 0);
        result = 31 * result + (getPowerUseRecords() != null ? getPowerUseRecords().hashCode() : 0);
        return result;
    }

    @Override
    protected Serializable pkVal() {
        return familyId;
    }
}