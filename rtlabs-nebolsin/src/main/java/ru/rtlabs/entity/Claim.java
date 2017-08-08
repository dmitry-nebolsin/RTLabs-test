package ru.rtlabs.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/** Сущность-заявка
 */
public class Claim {

    @Id
    private String id;

    private String orderId;

    private String serviceId;

    private String personId;

    private Date date;

    private String status;

    public Claim() {
    }

    public Claim(String orderId, String serviceId, String personId, Date date, String status) {
        this.orderId = orderId;
        this.serviceId = serviceId;
        this.personId = personId;
        this.date = date;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
