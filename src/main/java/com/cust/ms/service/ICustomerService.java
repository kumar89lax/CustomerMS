package com.cust.ms.service;

import java.util.List;

import com.cust.ms.exceptions.CustomerAlreadyExistsException;
import com.cust.ms.exceptions.CustomerNotFound;
import com.cust.ms.model.Customer;

public interface ICustomerService 
{
	public Customer getCustomer(Integer customerId) throws CustomerNotFound;
	
	public List<Customer> getAllCustomers();
	
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;
	
	public Customer updateCustomer(Integer customerId, Customer customer) throws CustomerNotFound;
	
	public boolean deleteCustomer(Integer customerId) throws CustomerNotFound;

}
