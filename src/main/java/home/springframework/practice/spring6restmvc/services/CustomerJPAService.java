package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.CustomerDTO;
import home.springframework.practice.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerJPAService implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return null;
    }

    @Override
    public List<CustomerDTO> customerList() {
        return null;
    }

    @Override
    public CustomerDTO saveNewBiryani(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public void updateById(UUID id, CustomerDTO biryaniDTO) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void patchById(UUID customerId, CustomerDTO customerDTO) {

    }
}
