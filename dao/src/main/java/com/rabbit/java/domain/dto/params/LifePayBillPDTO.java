package com.rabbit.java.domain.dto.params;


import com.rabbit.java.domain.model.LifePayBillDO;

import java.io.Serializable;

public class LifePayBillPDTO implements Serializable {

    private LifePayBillDO bill;

    private Integer billMonth;

    private Integer billId;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(Integer billMonth) {
        this.billMonth = billMonth;
    }

    public LifePayBillDO getBill() {
        return bill;
    }

    public void setBill(LifePayBillDO bill) {
        this.bill = bill;
    }
}
