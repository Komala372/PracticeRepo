package com.customerms.service;

import com.customerms.domain.Customer;
import com.customerms.exceptions.CustomerNotFoundException;
import com.customerms.exceptions.InvalidNameException;

import java.util.List;

public interface ICustomerService {

      Customer register(String firstName,String lastName) throws InvalidNameException;

      Customer findById(Integer id) throws CustomerNotFoundException;

      List<Customer> findCustomersByFirstNameAscendingId(String firstName) throws CustomerNotFoundException;



}
