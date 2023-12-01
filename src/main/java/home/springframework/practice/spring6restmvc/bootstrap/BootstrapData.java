package home.springframework.practice.spring6restmvc.bootstrap;

import home.springframework.practice.spring6restmvc.entities.Biryani;
import home.springframework.practice.spring6restmvc.mappers.BiryaniMapper;
import home.springframework.practice.spring6restmvc.mappers.CustomerMapper;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.models.BiryaniStyle;
import home.springframework.practice.spring6restmvc.models.CustomerDTO;
import home.springframework.practice.spring6restmvc.repositories.BiryaniRepository;
import home.springframework.practice.spring6restmvc.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BiryaniRepository biryaniRepository;
    private final CustomerRepository customerRepository;

    private final BiryaniMapper biryaniMapper;

    private final CustomerMapper customerMapper;

    public BootstrapData(BiryaniRepository biryaniRepository, CustomerRepository customerRepository, BiryaniMapper biryaniMapper, CustomerMapper customerMapper) {
        this.biryaniRepository = biryaniRepository;
        this.customerRepository = customerRepository;
        this.biryaniMapper = biryaniMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        BiryaniDTO birynai1 = BiryaniDTO.builder()
                .biryaniName("Karachi Biryani")
                .biryaniStyle(BiryaniStyle.KACCHI_AKHNI)
                .barcode("1232223")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(30)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        BiryaniDTO birynai2 = BiryaniDTO.builder()
                .biryaniName("Hyderbaadi  Biryani")
                .biryaniStyle(BiryaniStyle.PAKKI_AKHNI)
                .barcode("1232223")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(30)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        BiryaniDTO birynai3 = BiryaniDTO.builder()
                .biryaniName("Shadmani Biryani")
                .biryaniStyle(BiryaniStyle.PULAO)
                .barcode("1232223")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(30)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .customerName("Customer 1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();
        CustomerDTO customerDTO2 = CustomerDTO.builder()
                .customerName("Customer 2")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();
        CustomerDTO customerDTO3 = CustomerDTO.builder()
                .customerName("Customer 3")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();


        biryaniRepository.save(biryaniMapper.biryaniDTOToBiryani(birynai1));
        biryaniRepository.save(biryaniMapper.biryaniDTOToBiryani(birynai2));
        biryaniRepository.save(biryaniMapper.biryaniDTOToBiryani(birynai3));

        customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO1));
        customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO2));
        customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO3));

    }
}
