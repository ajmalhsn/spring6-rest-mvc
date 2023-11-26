package home.springframework.practice.spring6restmvc.controller;


import home.springframework.practice.spring6restmvc.models.Customer;
import home.springframework.practice.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;


    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return customerService.customerList();
    }

    @RequestMapping(method = RequestMethod.GET,value = "{Id}")
    public Customer getCustomerById(@PathVariable("Id") UUID id) {
        log.debug("Customer being retreived by ID" + id);
        return customerService.getCustomerById(id);

    }

}
