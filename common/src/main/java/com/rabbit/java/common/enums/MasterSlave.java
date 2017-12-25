package com.rabbit.java.common.enums;

/**
 * @author Mr.Rabbit
 */

public enum MasterSlave {

    MASTER("write"), SLAVE("read");

    private String type;

    MasterSlave(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
