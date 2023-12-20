package home.springframework.practice.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.springframework.practice.spring6restmvc.entities.Customer;
import home.springframework.practice.spring6restmvc.exceptions.NotFoundException;
import home.springframework.practice.spring6restmvc.models.CustomerDTO;
import home.springframework.practice.spring6restmvc.repositories.CustomerRepository;
import home.springframework.practice.spring6restmvc.services.CustomerService;
import home.springframework.practice.spring6restmvc.services.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

    @Test
    public void getCustomerByIdNotFound() throws Exception {

        given(customerService.getCustomerById(any(UUID.class))).willThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/customers/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Value Not Found"));
    }

    @Test
    public void getAllCustomer() throws Exception {
        List<CustomerDTO> customerList = customerServiceImpl.customerList();
        given(customerService.customerList()).willReturn(customerList);

        mockMvc.perform(get("/api/v1/customers/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(customerList)));
    }

    @Test
    public void getAllCustomerById() throws Exception {
        CustomerDTO customerDTO = customerServiceImpl.customerList().get(0);
        given(customerService.getCustomerById(any())).willReturn(Optional.of(customerDTO));

        mockMvc.perform(get("/api/v1/customers/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(customerDTO)));
    }

    @Test
    public void updateById() throws Exception {
        CustomerDTO customerDTO = customerServiceImpl.customerList().get(0);
        given(customerService.updateById(any(),any())).willReturn(Optional.of(customerDTO));

        mockMvc.perform(put("/api/v1/customers/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isNoContent());

    }

    @Test
    public void deleteById() throws Exception {
        given(customerService.deleteById(any())).willReturn(true);
        mockMvc.perform(delete("/api/v1/customers/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }
    @Test
    public void patchById() throws Exception {
        CustomerDTO customerDTO = customerServiceImpl.customerList().get(0);
        given(customerService.patchById(any(),any())).willReturn(Optional.of(customerDTO));
        mockMvc.perform(patch("/api/v1/customers/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isNoContent());

    }


}