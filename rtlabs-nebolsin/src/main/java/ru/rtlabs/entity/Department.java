package ru.rtlabs.entity;

/** Сущность - ведомство
 */
public class Department {

    private String name;

    private String enterpriseCode;

    public Department() {
    }

    public Department(String name, String enterpriseCode) {
        this.name = name;
        this.enterpriseCode = enterpriseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

}
