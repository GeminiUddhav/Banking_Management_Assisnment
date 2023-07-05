package com.bank_managemen.bank_management.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    @Id
    @Getter
    @Setter
    private String accountId;
    @Getter
    @Setter
    private String accountNumber;
    @Getter
    @Setter
    private String accountType;
    @Getter
    @Setter
    private Double balance;
    @Getter
    @Setter
    private String branch;
    @Getter
    @Setter
    private java.sql.Date accountCreatedOn;
    @Getter
    @Setter
    private Date accountUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Getter
    @Setter
    private Users user;

}