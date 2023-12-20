package home.springframework.practice.spring6restmvc.controller;


import home.springframework.practice.spring6restmvc.exceptions.NotFoundException;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.models.CustomerDTO;
import home.springframework.practice.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;


    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerDTO> getCustomers() {
        return customerService.customerList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{Id}")
    public CustomerDTO getCustomerById(@PathVariable("Id") UUID id) {
        log.debug("Customer being retreived by ID" + id);
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);

    }

    @PostMapping
    public ResponseEntity saveNewCustomer(@RequestBody CustomerDTO biryaniDTO) {
        CustomerDTO customerDTO = customerService.saveNewBiryani(biryaniDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "biryani/v1/" + customerDTO.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity deletedById(@PathVariable("customerId") UUID id) {

        if(! customerService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
    @PutMapping("/{customerId}")
    public ResponseEntity updateById(@PathVariable("customerId") UUID customerId,
                                     @RequestBody CustomerDTO customerDTO) {

        if(customerService.updateById(customerId, customerDTO).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("{customerId}")
    public ResponseEntity patchById(@PathVariable("customerId") UUID customerId,
                                    @RequestBody CustomerDTO customerDTO) {
        if(customerService.patchById(customerId, customerDTO).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
