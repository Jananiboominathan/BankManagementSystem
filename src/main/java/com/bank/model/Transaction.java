package com.bank.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String type;

    private double amount;

    private LocalDateTime transactionDate;

    public Transaction() {
    }

    public Transaction(String accountNumber, String type, double amount) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
    }

    @PrePersist
    public void beforeSave() {
        if (transactionDate == null) {
            transactionDate = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
}
