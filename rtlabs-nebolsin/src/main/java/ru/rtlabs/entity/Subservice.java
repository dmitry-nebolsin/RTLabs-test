package ru.rtlabs.entity;

public class Subservice {

    private String targetCode;

    private String name;

    public Subservice() {
    }

    public Subservice(String targetCode, String name) {
        this.targetCode = targetCode;
        this.name = name;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
