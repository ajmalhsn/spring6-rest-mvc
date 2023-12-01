package home.springframework.practice.spring6restmvc.controller;

import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
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

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest
@WebMvcTest(BiryaniController.class)
class BiryaniControllerTest {

//    @Autowired
//    BiryaniController biryaniController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BiryaniService biryaniService;

    BiryaniServiceImpl biryaniServiceImpl = new BiryaniServiceImpl();

    @SneakyThrows
    @Test
    public void getBiryaniId() {
        BiryaniDTO biryaniDTO = biryaniServiceImpl.biryaniList().get(0);

        given(biryaniService.getBiryaniById(Mockito.any(UUID.class))).willReturn(Optional.of(biryaniDTO));
        mockMvc.perform(get("/biryani/v1/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getBiryaniNotFoundId() throws Exception {


        given(biryaniService.getBiryaniById(Mockito.any(UUID.class))).willReturn(Optional.empty());
        mockMvc.perform(get("/biryani/v1/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


}