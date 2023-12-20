package home.springframework.practice.spring6restmvc;

import home.springframework.practice.spring6restmvc.controller.CustomerController;
import home.springframework.practice.spring6restmvc.entities.Customer;
import home.springframework.practice.spring6restmvc.exceptions.NotFoundException;
import home.springframework.practice.spring6restmvc.mappers.CustomerMapper;
import home.springframework.practice.spring6restmvc.models.CustomerDTO;
import home.springframework.practice.spring6restmvc.repositories.CustomerRepository;
import home.springframework.practice.spring6restmvc.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerControllerIT {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    public void getData() {

        List<CustomerDTO> customerDTOList = customerController.getCustomers();

        assertThat(customerDTOList.size()).isEqualTo(4);
    }

    @Test
    public void getCustomerByIdNotFound() {

        assertThrows(NotFoundException.class,() -> {
            customerController.getCustomerById(UUID.randomUUID());
        });

    }
    @Test
    public void getCustomerById() {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customerRepository.findAll().get(0));

        CustomerDTO customerDTO1 = customerController.getCustomerById(customerDTO.getId());

        assertThat(customerDTO1).isEqualTo(customerDTO);

    }

    @Test
    public void saveNewCustomer() {
        CustomerDTO customer = CustomerDTO.builder().customerName("New Customer").build();
        ResponseEntity responseEntity = customerController.saveNewCustomer(customer);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
    }
    @Test
    public void updateById() {
        CustomerDTO customer = customerMapper.customerToCustomerDTO(customerRepository.findAll().get(0));
        customer.setCustomerName("Updated");
        ResponseEntity responseEntity = customerController.updateById(customer.getId(),customer);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    public void patchById() {
        CustomerDTO customer = customerMapper.customerToCustomerDTO(customerRepository.findAll().get(0));
        customer.setCustomerName("Updated");
        ResponseEntity responseEntity = customerController.patchById(customer.getId(),customer);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    public void patchByIdNotFound() {
        CustomerDTO customer = customerMapper.customerToCustomerDTO(customerRepository.findAll().get(0));

        assertThrows(NotFoundException.class, () -> customerController.patchById(UUID.randomUUID(),customer));
    }

    @Test
    public void UpdateByIdNotFound() {
        CustomerDTO customer = customerMapper.customerToCustomerDTO(customerRepository.findAll().get(0));

        assertThrows(NotFoundException.class, () -> customerController.updateById(UUID.randomUUID(),customer));
    }

}
