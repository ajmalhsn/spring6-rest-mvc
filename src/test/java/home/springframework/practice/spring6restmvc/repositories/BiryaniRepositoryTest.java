package home.springframework.practice.spring6restmvc.repositories;


import home.springframework.practice.spring6restmvc.bootstrap.BootstrapData;
import home.springframework.practice.spring6restmvc.entities.Biryani;
import home.springframework.practice.spring6restmvc.mappers.BiryaniMapper;
import home.springframework.practice.spring6restmvc.mappers.CustomerMapper;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.models.BiryaniStyle;
import home.springframework.practice.spring6restmvc.repositories.BiryaniRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BiryaniRepositoryTest {

    @Autowired
    BiryaniRepository biryaniRepository;


    @Test
    public void saveBiryani() {

        Biryani savedBiryani = biryaniRepository.save(Biryani.builder()
                .biryaniName("My Biryani").build());
        assertThat(savedBiryani).isNotNull();
        assertThat(savedBiryani.getId()).isNotNull();

    }
    @Test
    public void checkInitialization()  {
        List<Biryani> biryaniList = biryaniRepository.findAll();
        assertThat(biryaniList).hasSize(4);
    }

}
