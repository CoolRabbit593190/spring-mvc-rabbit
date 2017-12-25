package com.rabbit.java.domain.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.Date;

@TableName("lifepay_family_desc")
public class LifePayFamilyDescDO extends Model<LifePayFamilyDescDO> implements Serializable {
    @TableId
    private Integer descId;

    private String centent;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastModifyDate;

    private Integer familyId;

    public Integer getDescId() {
        return descId;
    }

    public void setDescId(Integer descId) {
        this.descId = descId;
    }

    public String getCentent() {
        return centent;
    }

    public void setCentent(String centent) {
        this.centent = centent == null ? null : centent.trim();
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Override
    protected Serializable pkVal() {
        return descId;
    }
}