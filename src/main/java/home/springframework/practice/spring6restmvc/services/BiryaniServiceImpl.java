package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.Biryani;
import home.springframework.practice.spring6restmvc.models.BiryaniStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BiryaniServiceImpl implements BiryaniService {
    private Map<UUID,Biryani> map = new HashMap();

    public BiryaniServiceImpl() {

        Biryani birynai1 = Biryani.builder()
                .id(UUID.randomUUID())
                .version(12)
                .biryaniName("Karachi Biryani")
                .biryaniStyle(BiryaniStyle.KACCHI_AKHNI)
                .barcode("1232223")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(30)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Biryani birynai2 = Biryani.builder()
                .id(UUID.randomUUID())
                .version(12)
                .biryaniName("Hyderbaadi  Biryani")
                .biryaniStyle(BiryaniStyle.KACCHI_AKHNI)
                .barcode("1232223")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(30)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Biryani birynai3 = Biryani.builder()
                .id(UUID.randomUUID())
                .version(12)
                .biryaniName("Shadmani Biryani")
                .biryaniStyle(BiryaniStyle.KACCHI_AKHNI)
                .barcode("1232223")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(30)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        map.put(birynai1.getId(),birynai1);
        map.put(birynai2.getId(),birynai2);
        map.put(birynai3.getId(),birynai3);

    }

    public List<Biryani> biryaniList() {
        return new ArrayList<>(map.values());
    }
    @Override
    public Biryani getBiryaniById(UUID id) {

        log.debug("Log Debug for getting buryani by ID");
        return map.get(id);

    }
}
