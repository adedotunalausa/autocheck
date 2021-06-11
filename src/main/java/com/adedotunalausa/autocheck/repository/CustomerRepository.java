package com.adedotunalausa.autocheck.repository;

import com.adedotunalausa.autocheck.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByIsDeletedEqualsOrderByCreatedAtDesc(int value);
}
