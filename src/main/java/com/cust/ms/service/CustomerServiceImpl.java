package com.cust.ms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cust.ms.exceptions.CustomerAlreadyExistsException;
import com.cust.ms.exceptions.CustomerNotFound;
import com.cust.ms.model.Customer;
import com.cust.ms.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository custrepository;
	
	@Override
	public Customer getCustomer(Integer customerId) throws CustomerNotFound 
	{
		 Optional<Customer> CustomerOptional = this.custrepository.findById(customerId);

		 Customer custObj=null;
		 
		 try
		 {
	           
	        if(CustomerOptional.isPresent())
	        {
	        	custObj = CustomerOptional.get();
	        }
	        else
	        {
	        	throw new CustomerNotFound();
	        }
		 }
		 catch(CustomerNotFound cnfe)
		 {
			 System.out.println(cnfe.getMessage());
		 }
	        return custObj;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return this.custrepository.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException {

		Optional<Customer> CustomerOptional = this.custrepository.findById(customer.getCustId());

		Customer cObj=null;
		
		
			if(CustomerOptional.isPresent())
			{
				throw new CustomerAlreadyExistsException();
			}
			else
			{
				cObj = this.custrepository.save(customer);
			}
	        
        return cObj;
		
	}

	@Override
	public Customer updateCustomer(Integer customerId, Customer customer) throws CustomerNotFound {
		
		
		Optional<Customer> custOptional = this.custrepository.findById(customerId);

    	Customer cObj = null;
    	Customer updatedData = null;
    	
    	try
    	{
    		if(custOptional.isPresent())
    		{
    			System.out.println("Record Exists and ready for Update !!!");
        	
    			cObj = custOptional.get();
 
    			cObj.setCustName(customer.getCustName());
    			cObj.setCustEmail(customer.getCustEmail());
        	
    			updatedData = this.custrepository.save(cObj);
    		}
    		else
    		{
    			throw new CustomerNotFound();
    		}
        }
    	catch(CustomerNotFound cnfe)
    	{
    		System.out.println(cnfe.getMessage());
    	}
    	
        return updatedData;
	
	}

	@Override
	public boolean deleteCustomer(Integer customerId) throws CustomerNotFound 
	{
		Optional<Customer> custOptional = this.custrepository.findById(customerId);

        boolean deleteStatus = false;
      try
      {
    	  if(custOptional.isPresent())
    	  {
    		  this.custrepository.delete(custOptional.get());
    		  deleteStatus = true;
    	  }
    	  else
    	  {
    		  throw new CustomerNotFound();
    	  }
      }
      catch(CustomerNotFound cnfe)
      {
		 System.out.println(cnfe.getMessage());
	 }

        return deleteStatus;
	}

}
