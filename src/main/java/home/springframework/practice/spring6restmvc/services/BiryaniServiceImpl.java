package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.Biryani;
import home.springframework.practice.spring6restmvc.models.BiryaniStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BiryaniServiceImpl implements BiryaniService {
    private Map<UUID, Biryani> map = new HashMap();

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

        map.put(birynai1.getId(), birynai1);
        map.put(birynai2.getId(), birynai2);
        map.put(birynai3.getId(), birynai3);

    }

    public List<Biryani> biryaniList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Biryani saveNewBiryani(Biryani biryani) {

        Biryani newBiryani = Biryani.builder()
                .id(UUID.randomUUID())
                .version(biryani.getVersion())
                .biryaniName(biryani.getBiryaniName())
                .biryaniStyle(biryani.getBiryaniStyle())
                .price(biryani.getPrice())
                .barcode(biryani.getBarcode())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .quantityOnHand(biryani.getQuantityOnHand()).build();

        map.put(newBiryani.getId(), newBiryani);
        return newBiryani;
    }

    @Override
    public void updateById(UUID id, Biryani biryani) {
        Biryani existing = map.get(id);
        existing.setBiryaniName(biryani.getBiryaniName());
        existing.setBiryaniStyle(biryani.getBiryaniStyle());
        existing.setBarcode(biryani.getBarcode());
        existing.setQuantityOnHand(biryani.getQuantityOnHand());
        existing.setVersion(biryani.getVersion());

        map.put(existing.getId(), existing);

    }

    @Override
    public void deleteById(UUID id) {
        map.remove(id);
    }

    @Override
    public void patchById(UUID biryaniId, Biryani biryani) {
        Biryani existing = map.get(biryaniId);

        if (StringUtils.hasText(biryani.getBiryaniName())) {
            existing.setBiryaniName(biryani.getBiryaniName());
        }
        if (biryani.getBiryaniStyle() != null) {
            existing.setBiryaniStyle(biryani.getBiryaniStyle());
        }
        if (biryani.getPrice() != null) {
            existing.setPrice(biryani.getPrice());
        }
        if (StringUtils.hasText(biryani.getBarcode())) {
            existing.setBarcode(biryani.getBarcode());
        }
        if (biryani.getQuantityOnHand() != null) {
            existing.setQuantityOnHand(biryani.getQuantityOnHand());
        }
        if (biryani.getVersion() != null) {
            existing.setVersion(biryani.getVersion());
        }

    }

    @Override
    public Optional<Biryani> getBiryaniById(UUID id) {

        log.debug("Log Debug for getting buryani by ID");
        return Optional.of(map.get(id));

    }
}
