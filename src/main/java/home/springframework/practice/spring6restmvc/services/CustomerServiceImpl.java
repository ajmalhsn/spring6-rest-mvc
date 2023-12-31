package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.CustomerDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class CustomerServiceImpl implements CustomerService {
    Map<UUID, CustomerDTO> customerMap = new HashMap<>();

    public CustomerServiceImpl() {
        DataPopulation();
    }

    @PostConstruct
    private void DataPopulation() {

        CustomerDTO customerDTO1 = CustomerDTO.builder().id(UUID.randomUUID())
                .customerName("Customer 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();
        CustomerDTO customerDTO2 = CustomerDTO.builder().id(UUID.randomUUID())
                .customerName("Customer 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();
        CustomerDTO customerDTO3 = CustomerDTO.builder().id(UUID.randomUUID())
                .customerName("Customer 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();

        customerMap.put(customerDTO1.getId(), customerDTO1);
        customerMap.put(customerDTO2.getId(), customerDTO2);
        customerMap.put(customerDTO2.getId(), customerDTO3);


    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMap.get(id));
    }

    @Override
    public List<CustomerDTO> customerList() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDTO saveNewBiryani(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public Optional<CustomerDTO> updateById(UUID id, CustomerDTO biryaniDTO) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteById(UUID id) {

        return false;
    }

    @Override
    public Optional<CustomerDTO> patchById(UUID customerId, CustomerDTO customerDTO) {

        return Optional.empty();
    }
}
