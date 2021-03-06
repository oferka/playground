package com.example;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class CustomerMap {

    private Map<@Email(message="Must be a valid email") String, @NotNull Customer> customers;

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<String, Customer> customers) {
        this.customers = customers;
    }
}
