package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.mappers.CustomerMapper;
import home.springframework.practice.spring6restmvc.models.CustomerDTO;
import home.springframework.practice.spring6restmvc.repositories.BiryaniRepository;
import home.springframework.practice.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerJPAService implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final BiryaniRepository biryaniRepository;

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDTO(customerRepository.findById(id).orElse(null)));
    }

    @Override
    public List<CustomerDTO> customerList() {
        return customerRepository.findAll().stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO saveNewBiryani(CustomerDTO customerDTO) {
        return customerMapper.customerToCustomerDTO(customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO)));
    }

    @Override
    public Optional<CustomerDTO> updateById(UUID id, CustomerDTO customerDTO) {
        AtomicReference<Optional<CustomerDTO>> atomicCustomer = new AtomicReference<>();
        customerRepository.findById(id).ifPresentOrElse((existing) -> {
            existing.setCustomerName(customerDTO.getCustomerName());
            existing.setLastModifiedDate(LocalDateTime.now());
            atomicCustomer.set(Optional.of(customerMapper.customerToCustomerDTO(customerRepository.save(existing))));
        },() -> atomicCustomer.set(Optional.empty()));


        return atomicCustomer.get();
    }

    @Override
    public Boolean deleteById(UUID id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CustomerDTO> patchById(UUID customerId, CustomerDTO customerDTO) {
        AtomicReference<Optional<CustomerDTO>> atomicCustomer = new AtomicReference<>();
        customerRepository.findById(customerId).ifPresentOrElse((existing) -> {
            if(StringUtils.hasText(customerDTO.getCustomerName())) {
                existing.setCustomerName(customerDTO.getCustomerName());
            }
            existing.setLastModifiedDate(LocalDateTime.now());

            atomicCustomer.set(Optional.of(customerMapper.customerToCustomerDTO(customerRepository.save(existing))));
        },() -> atomicCustomer.set(Optional.empty()));


        return atomicCustomer.get();
    }
}
