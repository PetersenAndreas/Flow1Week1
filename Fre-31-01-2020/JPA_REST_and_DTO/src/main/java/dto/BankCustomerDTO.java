/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author andre
 */
public class BankCustomerDTO {
    private int id;
    private String fullName;
    private String accountNumber;
    private double balance;

    public BankCustomerDTO(BankCustomer bc) {
        this.id = bc.getId();
        this.fullName = bc.getFirstName() + " " + bc.getLastName();
        this.accountNumber = bc.getAccountNumber();
        this.balance = bc.getBalance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "{" + "\n" + "ID =" + id + "\n" + "FullName =" + fullName + "\n" + "AccountNumber = " + accountNumber + "\n" + "Balance = " + balance + "\n" +  "}";
    }
    
    
    
    
    

}
