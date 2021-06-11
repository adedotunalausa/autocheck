package com.adedotunalausa.autocheck.service;

import com.adedotunalausa.autocheck.model.Customer;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    void updateCustomer(Long customerId, String firstname, String lastname, String gender, String occupation,
                        String address, String city, String state, String email, String phoneNo);

}
