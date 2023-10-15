package com.customerms.Tester;

import com.customerms.domain.Customer;
import com.customerms.exceptions.CustomerNotFoundException;
import com.customerms.exceptions.InvalidNameException;
import com.customerms.service.CustomerServiceImpl;
import com.customerms.service.ICustomerService;

import java.util.List;

public class AppTest {
    ICustomerService customerService = new CustomerServiceImpl();

    public static void main(String[] args) {

        AppTest demo=new AppTest();
        demo.runApp();


    }

    void runApp() {
        registerAndDisplay("Devannsha","JainShahahiii");
        registerAndDisplay("Sachin","Tendulkarrrr");
        registerAndDisplay("Sahil","RajKumarSingh");
        registerAndDisplay("Puneeth","Rajkumaraaaa");
        registerAndDisplay("Sachin","Jainsdjshjshdj");
        registerAndDisplay("Sachin","GolatKarajshdjh");

        findByIdAndDisplay(2);
        findByFirstNameAndDisplay("Sachin");
    }

    void registerAndDisplay(String firstName, String lastName){
        try{
            customerService.register(firstName,lastName);
        }catch (InvalidNameException e){
            System.out.println("invalid name-"+e.getMessage());
        }
    }

    void findByIdAndDisplay(Integer id){
        try{
            Customer customer = customerService.findById(id);
        }catch(CustomerNotFoundException e){
            System.out.println("Customer not found-"+e.getMessage());
        }
    }

    void findByFirstNameAndDisplay(String firstName){
        try {
            List<Customer> list = customerService.findCustomersByFirstNameAscendingId(firstName);
            for (Customer customer : list) {
                System.out.println("Customer id = " + customer.getId() + ", name= " + customer.getFirstName() + " " + customer.getLastName());
            }
        }catch (CustomerNotFoundException e){
            System.out.println("Customer not found-"+e.getMessage());
        }
    }

}
