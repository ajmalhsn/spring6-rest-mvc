package home.springframework.practice.spring6restmvc.bootstrap;

import home.springframework.practice.spring6restmvc.mappers.BiryaniMapper;
import home.springframework.practice.spring6restmvc.mappers.BiryaniMapperImpl;
import home.springframework.practice.spring6restmvc.mappers.CustomerMapper;
import home.springframework.practice.spring6restmvc.mappers.CustomerMapperImpl;
import home.springframework.practice.spring6restmvc.repositories.BiryaniRepository;
import home.springframework.practice.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BootstrapDataTest {

    @Autowired
    BiryaniRepository biryaniRepository;
    @Autowired
    CustomerRepository customerRepository;

    BiryaniMapper biryaniMapper;


    CustomerMapper customerMapper;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        biryaniMapper = new BiryaniMapperImpl();
        customerMapper = new CustomerMapperImpl();
        bootstrapData = new BootstrapData(biryaniRepository,customerRepository,biryaniMapper,customerMapper);

    }

    @Test
    public void checkInitialization() throws Exception {
        bootstrapData.run(null);
        assertThat(biryaniRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}