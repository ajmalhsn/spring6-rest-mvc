package home.springframework.practice.spring6restmvc.repositories;


import home.springframework.practice.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void saveCustomer() {

        Customer savedCustomer = customerRepository.save(Customer.builder()
                .customerName("My Customer").build());

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isNotNull();
    }
    @Test
    public void checkInitalization() {
        assertThat(customerRepository.findAll()).hasSize(4);
    }

}
