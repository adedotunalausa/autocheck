package com.adedotunalausa.week8taskadedotunalausa.service.implementation;

import com.adedotunalausa.week8taskadedotunalausa.model.Customer;
import com.adedotunalausa.week8taskadedotunalausa.repository.CustomerRepository;
import com.adedotunalausa.week8taskadedotunalausa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAllByIsDeletedEqualsOrderByCreatedAtDesc(0);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Customer foundCustomer = null;
        if (this.customerRepository.findById(customerId).isPresent()) {
            foundCustomer = this.customerRepository.findById(customerId).get();
        }

        return foundCustomer;
    }

    @Override
    public void updateCustomer(Long customerId, String firstname, String lastname, String gender, String occupation,
                               String address, String city, String state, String email, String phoneNo) {
        Customer customer = getCustomerById(customerId);
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setGender(gender);
        customer.setOccupation(occupation);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setState(state);
        customer.setEmail(email);
        customer.setPhoneNo(phoneNo);
        customerRepository.save(customer);
    }
}
