package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.models.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    public Optional<CustomerDTO> getCustomerById(UUID id);

    public List<CustomerDTO> customerList();

    CustomerDTO saveNewBiryani(CustomerDTO customerDTO);

    Optional<CustomerDTO> updateById(UUID id, CustomerDTO biryaniDTO);

    Boolean deleteById(UUID id);

    Optional<CustomerDTO> patchById(UUID customerId, CustomerDTO customerDTO);

}
