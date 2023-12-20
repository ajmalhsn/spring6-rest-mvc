package home.springframework.practice.spring6restmvc.repositories;


import home.springframework.practice.spring6restmvc.entities.Biryani;

import home.springframework.practice.spring6restmvc.models.BiryaniStyle;

import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.math.BigDecimal;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BiryaniRepositoryTest {

    @Autowired
    BiryaniRepository biryaniRepository;

    @Test
    public void saveBiryaniNameTooLong() {
        assertThrows(ConstraintViolationException.class, () -> {
            Biryani savedBiryani = biryaniRepository.save(Biryani.builder()
                    .biryaniName("My Biryani 012345678901234567890123456789012345678930dvkggggggggggggggggggggggggggggggggggggggg")
                    .biryaniStyle(BiryaniStyle.KALYANI)
                    .barcode("292281")
                    .price(BigDecimal.valueOf(10.99)).build());

            biryaniRepository.flush();
        });


    }

    @Test
    public void saveBiryani() {

        Biryani savedBiryani = biryaniRepository.save(Biryani.builder()
                .biryaniName("My Biryani")
                .biryaniStyle(BiryaniStyle.KALYANI)
                .barcode("292281")
                .price(BigDecimal.valueOf(10.99)).build());

        biryaniRepository.flush();


        assertThat(savedBiryani).isNotNull();
        assertThat(savedBiryani.getId()).isNotNull();

    }
    @Test
    public void checkInitialization()  {
        List<Biryani> biryaniList = biryaniRepository.findAll();
        assertThat(biryaniList).hasSize(0);
    }

}
