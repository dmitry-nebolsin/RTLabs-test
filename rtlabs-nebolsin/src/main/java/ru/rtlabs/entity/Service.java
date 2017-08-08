package ru.rtlabs.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

/** Сущность - услуга
 */
public class Service {

    @Id
    private String id;

    private String targetCode;

    private String formCode;

    private String receiverCategory;

    private List<Subservice> subservices;

    private Department department;

    public Service() {
    }

    public Service(String targetCode, String formCode, String receiverCategory, List<Subservice> subservices, Department department) {
        this.targetCode = targetCode;
        this.formCode = formCode;
        this.receiverCategory = receiverCategory;
        this.subservices = subservices;
        this.department = department;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getReceiverCategory() {
        return receiverCategory;
    }

    public void setReceiverCategory(String receiverCategory) {
        this.receiverCategory = receiverCategory;
    }

    public List<Subservice> getSubservices() {
        return subservices;
    }

    public void setSubservices(List<Subservice> subservices) {
        this.subservices = subservices;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
