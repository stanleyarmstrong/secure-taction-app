package com.secure.taction.SeniorProject.dtos.transaction;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class TransactionDto {
    
    private String transactionId;
    private String accountId;
    private BigDecimal amount;
    private String location;
    private String vendor;
    private List<String> categories = new LinkedList<>();

    public TransactionDto() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionDto withTransactionId(String transactionId) {
        setTransactionId(transactionId);
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public TransactionDto withAccountId(String accountId) {
        setAccountId(accountId);
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionDto withAmount(BigDecimal amount) {
        setAmount(amount);
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TransactionDto withLocation(String location) {
        setLocation(location);
        return this;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public TransactionDto withVendor(String vendor) {
        setVendor(vendor);
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public TransactionDto withCategories(List<String> categories) {
        setCategories(categories);
        return this;
    }

    public TransactionDto addCategory(String category) {
        if (this.categories == null) {
            this.categories = new LinkedList<>();
        }
        this.categories.add(category);
        return this;
    }

}
