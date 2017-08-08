package ru.rtlabs.entity;

import java.io.Serializable;

public class ReportRow implements Serializable {

    private String orderNumber;

    private String date;

    private String formCode;

    private String status;

    private String fio;

    private String type;

    private String serviceName;

    private String subserviceName;

    private String enterpriseName;

    public ReportRow() {
    }

    public ReportRow(String orderNumber, String date, String formCode, String status, String fio, String type, String serviceName, String subserviceName, String enterpriseName) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.formCode = formCode;
        this.status = status;
        this.fio = fio;
        this.type = type;
        this.serviceName = serviceName;
        this.subserviceName = subserviceName;
        this.enterpriseName = enterpriseName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSubserviceName() {
        return subserviceName;
    }

    public void setSubserviceName(String subserviceName) {
        this.subserviceName = subserviceName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
