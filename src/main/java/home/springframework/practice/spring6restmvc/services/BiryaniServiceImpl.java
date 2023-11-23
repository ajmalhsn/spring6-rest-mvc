package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.Biryani;
import home.springframework.practice.spring6restmvc.models.BiryaniStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BiryaniServiceImpl implements BiryaniService {
    @Override
    public Biryani getBiryaniById(UUID id) {

        log.debug("Log Debug for getting buryani by ID");
        return Biryani.builder()
                .id(id)
                .version(12)
                .biryaniName("Karachi Biryani")
                .biryaniStyle(BiryaniStyle.KACCHI_AKHNI)
                .barcode("1232223")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(30)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

    }
}
