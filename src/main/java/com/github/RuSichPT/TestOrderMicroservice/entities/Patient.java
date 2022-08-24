package com.github.RuSichPT.TestOrderMicroservice.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.sql.Date;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD) // or @XmlType(propOrder = {"id", "orderStatusId", "customerName", "customerPhone", "customerComment", "orderItems"})
public class Patient {
    private int id;

    private String firstName;

    private String midName;

    private String lastName;

    private int genderId;

    private Date birthday;

    private String phone;

    private String email;

    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", midName='" + midName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genderId=" + genderId +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && genderId == patient.genderId && Objects.equals(firstName, patient.firstName) && Objects.equals(midName, patient.midName) && Objects.equals(lastName, patient.lastName) && Objects.equals(birthday, patient.birthday) && Objects.equals(phone, patient.phone) && Objects.equals(email, patient.email) && Objects.equals(address, patient.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, midName, lastName, genderId, birthday, phone, email, address);
    }
}
