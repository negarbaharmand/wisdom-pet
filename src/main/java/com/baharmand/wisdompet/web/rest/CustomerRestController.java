package com.baharmand.wisdompet.web.rest;


import com.baharmand.wisdompet.service.CustomerService;
import com.baharmand.wisdompet.web.exceptions.BadRequestException;
import com.baharmand.wisdompet.web.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public List<Customer> getAll(@RequestParam(name = "email", required = false) String email) {
        return this.customerService.getAllCustomer(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return this.customerService.createOrUpdate(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") long id) {
        return this.customerService.getCustomer(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        if (id != customer.getCustomerId()) {
            throw new BadRequestException("ids do not match");
        }
        return this.customerService.createOrUpdate(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteCustomer(@PathVariable("id") long id) {
        this.customerService.deleteCustomer(id);
    }
}
