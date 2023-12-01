package home.springframework.practice.spring6restmvc;

import home.springframework.practice.spring6restmvc.controller.BiryaniController;
import home.springframework.practice.spring6restmvc.entities.Biryani;
import home.springframework.practice.spring6restmvc.exceptions.NotFoundException;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.repositories.BiryaniRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BiryaniControllerIT {

    @Autowired
    BiryaniController biryaniController;

    @Autowired
    BiryaniRepository biryaniRepository;


    @Test
    public void findByIdNotFound() {

        assertThrows(NotFoundException.class, () -> {
            biryaniController.getBiryaniById(UUID.randomUUID());
        });
    }

    @Test
    public void findById() {
        Biryani biryani = biryaniRepository.findAll().get(0);
        BiryaniDTO biryaniDTO = biryaniController.getBiryaniById(biryani.getId());

        assertThat(biryaniDTO).isNotNull();
    }

    @Test
    public void checkData() {
        List<BiryaniDTO> biryaniDTOList = biryaniController.getBiryaniList();

        assertThat(biryaniDTOList.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    public void emptyDataCheck() {
        biryaniRepository.deleteAll();
        List<BiryaniDTO> biryaniDTOList = biryaniController.getBiryaniList();

        assertThat(biryaniDTOList.size()).isEqualTo(0);
    }


}
