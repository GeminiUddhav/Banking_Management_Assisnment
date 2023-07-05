package com.bank_managemen.bank_management.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor

public class Users {
    @Id
    @Getter
    @Setter
    private int userId;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String aadharNumber;
    @Getter
    @Setter
    private java.sql.Date dateOfBirth;
    @Getter
    @Setter
    private int age;
    @Getter
    @Setter
    private String address;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Accounts> accountsList;

}
