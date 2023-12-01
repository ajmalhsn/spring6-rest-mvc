package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.models.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    public CustomerDTO getCustomerById(UUID id);

    public List<CustomerDTO> customerList();

    CustomerDTO saveNewBiryani(CustomerDTO customerDTO);

    void updateById(UUID id, CustomerDTO biryaniDTO);

    void deleteById(UUID id);

    void patchById(UUID customerId, CustomerDTO customerDTO);

}
