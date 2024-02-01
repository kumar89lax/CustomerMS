package com.cust.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cust.ms.model.Customer;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> 
{

}