package com.enoca.challenge.service;

import com.enoca.challenge.entity.Customer;
import com.enoca.challenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setOrders(updatedCustomer.getOrders());
            customerRepository.save(customer);
        }
        return customer;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
