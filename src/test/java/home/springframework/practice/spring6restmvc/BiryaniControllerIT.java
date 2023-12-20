package home.springframework.practice.spring6restmvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.springframework.practice.spring6restmvc.controller.BiryaniController;
import home.springframework.practice.spring6restmvc.entities.Biryani;
import home.springframework.practice.spring6restmvc.exceptions.NotFoundException;
import home.springframework.practice.spring6restmvc.mappers.BiryaniMapper;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.repositories.BiryaniRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BiryaniControllerIT {

    @Autowired
    BiryaniController biryaniController;

    @Autowired
    BiryaniRepository biryaniRepository;

    @Autowired
    BiryaniMapper biryaniMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void patchBiryaniById() throws Exception {
        Biryani biryani = biryaniRepository.findAll().get(0);

        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("biryaniName","New Name0123456789012345678901234567890123456789iefjijinin");

       MvcResult mvcResult = mockMvc.perform(patch("/biryani/v1/{biryaniId}", biryani.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest()).andReturn();

       System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testNotFoundForUpdateById() {
        assertThrows(NotFoundException.class,() -> {
            biryaniController.updateById(UUID.randomUUID(),BiryaniDTO.builder().build());
        });
    }


    @Rollback
    @Transactional
    @Test
    public void updateById() {
        Biryani biryani = biryaniRepository.findAll().get(0);
        BiryaniDTO biryaniDTO = biryaniMapper.biryaniToBiryaniDTO(biryani
                .builder()
                .biryaniName("Updated").build());

        ResponseEntity entity = biryaniController.updateById(biryani.getId(),biryaniDTO);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));

        Biryani updatedBiryani = biryaniRepository.findById(biryani.getId()).get();

        assertThat(updatedBiryani.getBiryaniName()).isEqualTo("Updated");



    }
    @Test
    public void deleteByIdNotFound() {

        assertThrows(NotFoundException.class, () -> {
            biryaniController.deletedById(UUID.randomUUID());
        });
    }


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

    @Rollback
    @Transactional
    @Test
    public void saveNewBiryani() {
        BiryaniDTO biryani = BiryaniDTO.builder().biryaniName("Good Biryani").build();

        ResponseEntity entity = biryaniController.addBiryani(biryani);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getHeaders().getLocation()).isNotNull();

        String[] UUIDLocation = entity.getHeaders().getLocation().getPath().split("/");

        assertThat(biryaniRepository.findById(UUID.fromString(UUIDLocation[2]))).isNotNull();



    }

    @Test
    public void checkData() {
        List<BiryaniDTO> biryaniDTOList = biryaniController.getBiryaniList();

        assertThat(biryaniDTOList.size()).isEqualTo(4);
    }

    @Rollback
    @Transactional
    @Test
    public void emptyDataCheck() {
        biryaniRepository.deleteAll();
        List<BiryaniDTO> biryaniDTOList = biryaniController.getBiryaniList();

        assertThat(biryaniDTOList.size()).isEqualTo(0);
    }

    @Test
    public void deleteById() {
        Biryani biryani = biryaniRepository.findAll().get(0);
        ResponseEntity responseEntity = biryaniController.deletedById(biryani.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));

        assertThat(biryaniRepository.findById(biryani.getId())).isEmpty();

    }

    @Transactional
    @Rollback
    @Test
    public void patchById() {
        Biryani biryani = biryaniRepository.findAll().get(0);
        BiryaniDTO biryaniDTO = biryaniMapper.biryaniToBiryaniDTO(biryani);
        biryaniDTO.setBiryaniName("Updated");
        ResponseEntity responseEntity = biryaniController.patchById(biryani.getId(),biryaniDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));

        assertThat(biryaniRepository.findById(biryani.getId())).isNotEmpty();
        assertThat(biryaniRepository.findById(biryani.getId()).get().getBiryaniName()).isEqualTo("Updated");

    }

}
