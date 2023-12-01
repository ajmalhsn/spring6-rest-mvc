package home.springframework.practice.spring6restmvc.mappers;

import home.springframework.practice.spring6restmvc.entities.Biryani;
import home.springframework.practice.spring6restmvc.entities.Customer;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.models.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDTOToCustomer(CustomerDTO dto);
    CustomerDTO customerToCustomerDTO(Customer customer);
}
