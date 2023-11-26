package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.Biryani;
import home.springframework.practice.spring6restmvc.models.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class CustomerServiceImpl implements CustomerService {
    Map<UUID,Customer> customerMap = new HashMap<>();
    public CustomerServiceImpl() {

    }

    @PostConstruct
    private void DataPopulation() {

        Customer customer1 = Customer.builder().id(UUID.randomUUID())
                .customerName("Customer 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();
        Customer customer2 = Customer.builder().id(UUID.randomUUID())
                .customerName("Customer 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();
        Customer customer3 = Customer.builder().id(UUID.randomUUID())
                .customerName("Customer 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();

        customerMap.put(customer1.getId(),customer1);
        customerMap.put(customer2.getId(),customer2);
        customerMap.put(customer2.getId(),customer3);


    }
    @Override
    public Customer getCustomerById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public List<Customer> customerList() {
        return new ArrayList<>(customerMap.values());
    }
}
