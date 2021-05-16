package com.adedotunalausa.week8taskadedotunalausa.service;

import com.adedotunalausa.week8taskadedotunalausa.model.Customer;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    void updateCustomer(Long customerId, String firstname, String lastname, String gender, String occupation,
                        String address, String city, String state, String email, String phoneNo);

}
