package com.customerms.service;

import com.customerms.domain.Customer;
import com.customerms.exceptions.CustomerNotFoundException;
import com.customerms.exceptions.InvalidNameException;

import java.util.*;

public class CustomerServiceImpl implements ICustomerService {

    Map<Integer, Customer> customerMap = new HashMap<>();

    Integer index = 0;

    Integer generateId() {
        return ++index;
    }

    @Override
    public Customer register(String firstName, String lastName) throws InvalidNameException {
        Integer id = generateId();
        if ((firstName.length() > 2 && firstName.length() < 10) && (!(lastName.length() > 2 && lastName.length() < 10))) {
            customerMap.put(id, new Customer(id, firstName, lastName));
            System.out.println("customer: " + " id= " + id + "," + "firstName= " + firstName + ",lastName = " + lastName);
            return customerMap.get(id);
        } else {
            throw new InvalidNameException("Invalid name");
        }
    }

    @Override
    public Customer findById(Integer id) throws CustomerNotFoundException {
        if (id < 1) {
            System.out.println("Invalid id");
        }
        Customer customer = customerMap.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return customer;
    }

    @Override
    public List<Customer> findCustomersByFirstNameAscendingId(String firstName) throws CustomerNotFoundException {
        if (firstName.length() < 3) {
            System.out.println("Insufficient text for search");
        }
        List<Customer> list = new ArrayList<>();
        for (Map.Entry<Integer, Customer> entry : customerMap.entrySet()) {
            if (entry.getValue().getFirstName().startsWith(firstName)) {
                System.out.println("Entry inserting is:" + entry);
                list.add(entry.getValue());
            }
        }
        Collections.sort(list, valueComparator);
        if (list == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return list;
    }


    Comparator<Customer> valueComparator = new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            int id1 = o1.getId();
            int id2 = o2.getId();
            return id1-id2;
        }
    };
}
