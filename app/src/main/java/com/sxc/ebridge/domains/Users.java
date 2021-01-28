package com.sxc.ebridge.domains;

public class Users {
    String id,name,dob,contact,department,batch;

    public Users() {
    }

    public Users(String id, String name, String dob, String contact, String department, String batch) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.contact = contact;
        this.department = department;
        this.batch = batch;
    }

    public Users(String id, String name, String contact, String department) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
