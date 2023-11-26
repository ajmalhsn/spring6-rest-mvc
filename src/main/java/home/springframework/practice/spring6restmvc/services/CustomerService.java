package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    public Customer getCustomerById(UUID id);

    public List<Customer> customerList();
}
