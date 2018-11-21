package com.acloudtiger.springcloudsecurityresourceserver.model;

public class Account {

    private double accountNumber;
    private String accountName;
    private String emailAddress;

    public Account(double accountNumber, String accountName, String emailAddress) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.emailAddress = emailAddress;
    }

    public double getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(double accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountName='" + accountName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
