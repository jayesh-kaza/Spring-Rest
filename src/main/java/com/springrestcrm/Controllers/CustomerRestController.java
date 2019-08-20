package com.springrestcrm.Controllers;

import com.springrestcrm.Entities.Customer;
import com.springrestcrm.Exceptions.CustomerNotFoundException;
import com.springrestcrm.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    //autowire customer service
    @Autowired
    private CustomerService customerService;

    //add mapping for GET/customers
    @GetMapping("/customers")
    public List<Customer> getCustomers()
    {
        return customerService.getCustomers();
    }

    //add mapping for GET/customers/{customerId}
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId)
    {
        Customer customer = customerService.getCustomer(customerId);
        if(customer==null)
            throw new CustomerNotFoundException("Customer id not found - "+customerId);
        return customer;
    }

    //add mapping for POST/customers - add new customer
    //@RequestBody is used to get the json data
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer)
    {
        //incase the id is passed in json, set it to '0' so that hibernates 'inserts' it
        //else hibernate will update the table
        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }

    //add mapping for PUT/customers - update a customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer)
    {
        //id is not set as we are updating
        customerService.saveCustomer(customer);
        return customer;
    }

    //add mapping for DELETE/customers/{customerId} - delete a customer
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId)
    {
        Customer tempCustomer = customerService.getCustomer(customerId);

        if(tempCustomer==null)
            throw new CustomerNotFoundException("Customer id not found - "+customerId);

        customerService.deleteCustomer(customerId);

        return "Deleted customer id - "+customerId;
    }


}
