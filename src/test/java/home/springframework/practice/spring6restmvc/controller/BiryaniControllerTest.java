package home.springframework.practice.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.models.BiryaniStyle;
import home.springframework.practice.spring6restmvc.services.BiryaniService;
import home.springframework.practice.spring6restmvc.services.BiryaniServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@WebMvcTest(BiryaniController.class)
class BiryaniControllerTest {

//    @Autowired
//    BiryaniController biryaniController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BiryaniService biryaniService;

    @Autowired
    ObjectMapper objectMapper;



    BiryaniServiceImpl biryaniServiceImpl = new BiryaniServiceImpl();

    @SneakyThrows
    @Test
    public void getBiryaniId() {
        BiryaniDTO biryaniDTO = biryaniServiceImpl.biryaniList().get(0);

        given(biryaniService.getBiryaniById(any(UUID.class))).willReturn(Optional.of(biryaniDTO));
        mockMvc.perform(get("/biryani/v1/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
    @SneakyThrows
    @Test
    public void updateBiryaniById() {
        BiryaniDTO biryaniDTO = biryaniServiceImpl.biryaniList().get(0);
//
        given(biryaniService.updateById(any(),any())).willReturn(Optional.of(biryaniDTO));
        mockMvc.perform(put("/biryani/v1/{biryaniId}", biryaniDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(biryaniDTO)))
                .andExpect(status().isNoContent());
        verify(biryaniService,times(1)).updateById(any(),any());

    }
    @SneakyThrows
    @Test
    public void updateBiryaniNullBeer() {
        BiryaniDTO biryaniDTO = BiryaniDTO.builder().id(UUID.randomUUID()).build();

        MvcResult result = mockMvc.perform(put("/biryani/v1/{biryaniId}", biryaniDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(biryaniDTO)))
                .andExpect(jsonPath("$.length()",is(6)))
                .andExpect(status().isBadRequest()).andReturn();

        System.out.println(result.getResponse().getContentAsString());


    }

    @SneakyThrows
    @Test
    public void addBiryani() {
        BiryaniDTO biryaniDTO = BiryaniDTO.builder().id(UUID.randomUUID()).biryaniName("BiryaniName")
                .biryaniStyle(BiryaniStyle.PULAO)
                .price(new BigDecimal(9.00)).barcode("rijijin").build();
        given(biryaniService.saveNewBiryani(biryaniDTO)).willReturn(biryaniDTO);
        mockMvc.perform(post("/biryani/v1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(biryaniDTO)))
                .andExpect(status().isCreated());

    }

    @SneakyThrows
    @Test
    public void addBiryaniNullBiryaniName() {
        BiryaniDTO biryaniDTO = BiryaniDTO.builder().build();
        given(biryaniService.saveNewBiryani(any(BiryaniDTO.class))).willReturn(biryaniServiceImpl.biryaniList().get(1));
        MvcResult result = mockMvc.perform(post("/biryani/v1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(biryaniDTO)))
                .andExpect(jsonPath("$.length()",is(6)))
                .andExpect(status().isBadRequest()).andReturn();

        System.out.println(result.getResponse().getContentAsString());


    }


    @SneakyThrows
    @Test
    public void deleteBiryaniById() {
        BiryaniDTO biryaniDTO = biryaniServiceImpl.biryaniList().get(0);
//
        given(biryaniService.deleteById(any())).willReturn(true);
        mockMvc.perform(delete("/biryani/v1/{biryaniId}", biryaniDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void getBiryaniNotFoundId() throws Exception {


        given(biryaniService.getBiryaniById(any(UUID.class))).willReturn(Optional.empty());
        mockMvc.perform(get("/biryani/v1/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @SneakyThrows
    @Test
    public void patchBiryaniById() {
        BiryaniDTO biryaniDTO = biryaniServiceImpl.biryaniList().get(0);
//
        given(biryaniService.patchById(any(),any())).willReturn(Optional.of(biryaniDTO));
        mockMvc.perform(patch("/biryani/v1/{biryaniId}", biryaniDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(biryaniDTO)))
                .andExpect(status().isNoContent());

    }

    @SneakyThrows
    @Test
    public void patchBiryaniByIdNotFound() {
        BiryaniDTO biryaniDTO = biryaniServiceImpl.biryaniList().get(0);
//
//        given(biryaniService.patchById(any(),any())).willReturn(Optional.of(biryaniDTO));
        mockMvc.perform(patch("/biryani/v1/{biryaniId}", biryaniDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(biryaniDTO)))
                .andExpect(status().isNotFound());

    }


}