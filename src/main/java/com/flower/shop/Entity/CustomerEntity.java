package com.flower.shop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int customerId;
    private String customerName;
    private String customerEmail;
    public CustomerEntity(String customerName, String customerEmail, String customerNumber, String customerPassword) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerNumber = customerNumber;
        this.customerPassword = customerPassword;
    }
    public CustomerEntity() {
    }
    @Override
    public String toString() {
        return "CustomerEntity [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
                + customerEmail + ", customerNumber=" + customerNumber + ", customerPassword=" + customerPassword + "]";
    }
    private String customerNumber;
    private String customerPassword;
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public String getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
    public String getCustomerPassword() {
        return customerPassword;
    }
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}
