package com.cust.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cust.ms.exceptions.CustomerAlreadyExistsException;
import com.cust.ms.exceptions.CustomerNotFound;
import com.cust.ms.model.Customer;
import com.cust.ms.service.ICustomerService;

@RestController
@RequestMapping("api/v1")
public class CustomerController 
{
	@Autowired
	private ICustomerService customerservice;
	
	private ResponseEntity<?> responseentity;
	
	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomerHandler(@RequestBody Customer CustomerObj)
	{
		Customer Customercreated=null;
		try {
			
			Customercreated = this.customerservice.addCustomer(CustomerObj);
					
			responseentity = new ResponseEntity(Customercreated,HttpStatus.CREATED);

			if(Customercreated == null)
			{
				throw new CustomerAlreadyExistsException();
			}
			
		} catch (CustomerAlreadyExistsException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Error ..........."+ e.getMessage());
			
			e.printStackTrace();
			
			responseentity = new ResponseEntity("Customer Trying to add already exists ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseentity;
	}
	

	@GetMapping("/getCDetails")
	public ResponseEntity<?> getAllCustomerHandler()
	{
		responseentity = new ResponseEntity(this.customerservice.getAllCustomers(),HttpStatus.OK);
		return responseentity;
	}
	
	@GetMapping("/getCustomerById/{cid}")
	public ResponseEntity<?> getCustomerByIdHandler(@PathVariable Integer cid)
	{
		Customer cObj=null;
		try {
			cObj = this.customerservice.getCustomer(cid);
		} catch (CustomerNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseentity = new ResponseEntity(cObj,HttpStatus.OK);
		return responseentity;
	}
	
	@DeleteMapping("/delCustomerById/{cid}")
	public ResponseEntity<?> deleteCustomerByIdHandler(@PathVariable Integer cid) throws CustomerNotFound
	{
		boolean status = this.customerservice.deleteCustomer(cid);
		responseentity = new ResponseEntity("Customer deleted successfully !!!",HttpStatus.OK);
		return responseentity;
	}
	

	@PutMapping("/updateCustomerById/{cid}")
	public ResponseEntity<?> updateCustomerByIdHandler(@RequestBody Customer CustomerObj, @PathVariable Integer cid) throws CustomerNotFound
	{
		Customer cObj = this.customerservice.updateCustomer(cid,CustomerObj);
		responseentity = new ResponseEntity(cObj,HttpStatus.OK);
		return responseentity;
	}
}