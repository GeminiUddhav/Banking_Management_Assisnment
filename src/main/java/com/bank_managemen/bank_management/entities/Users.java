package com.bank_managemen.bank_management.entities;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table

public class Users {
    @Id
    private int userId;
    private String firstName;
    private String lastName;
    private String aadharNumber;
    private java.sql.Date dateOfBirth;
    private int age;
    private String address;
    public Users() {
        super();
    }
    private String mobileNo;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Accounts> accountsList;

    public Users(int userId, String firstName, String lastName, String aadharNumber, Date dateOfBirth, int age, String address, String mobileNo, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.aadharNumber = aadharNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
