package ru.rtlabs.entity;

import org.springframework.data.annotation.Id;

/** Сущность - заявитель
 */
public class Person {
    @Id
    private String id;

    private String fio;

    private String orgType;

    public Person() {
    }

    public Person(String fio, String orgType) {
        this.fio = fio;
        this.orgType = orgType;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
