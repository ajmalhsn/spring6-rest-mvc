package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
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
    private Map<UUID, BiryaniDTO> map = new HashMap();

    public BiryaniServiceImpl() {

        BiryaniDTO birynai1 = BiryaniDTO.builder()
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
        BiryaniDTO birynai2 = BiryaniDTO.builder()
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
        BiryaniDTO birynai3 = BiryaniDTO.builder()
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

    public List<BiryaniDTO> biryaniList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public BiryaniDTO saveNewBiryani(BiryaniDTO biryaniDTO) {

        BiryaniDTO newBiryaniDTO = BiryaniDTO.builder()
                .id(UUID.randomUUID())
                .version(biryaniDTO.getVersion())
                .biryaniName(biryaniDTO.getBiryaniName())
                .biryaniStyle(biryaniDTO.getBiryaniStyle())
                .price(biryaniDTO.getPrice())
                .barcode(biryaniDTO.getBarcode())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .quantityOnHand(biryaniDTO.getQuantityOnHand()).build();

        map.put(newBiryaniDTO.getId(), newBiryaniDTO);
        return newBiryaniDTO;
    }

    @Override
    public Optional<BiryaniDTO> updateById(UUID id, BiryaniDTO biryaniDTO) {
        BiryaniDTO existing = map.get(id);
        existing.setBiryaniName(biryaniDTO.getBiryaniName());
        existing.setBiryaniStyle(biryaniDTO.getBiryaniStyle());
        existing.setBarcode(biryaniDTO.getBarcode());
        existing.setQuantityOnHand(biryaniDTO.getQuantityOnHand());
        existing.setVersion(biryaniDTO.getVersion());

        map.put(existing.getId(), existing);

        return Optional.of(existing);
    }

    @Override
    public Boolean deleteById(UUID id) {
        if(map.containsKey(id)) {
            map.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BiryaniDTO> patchById(UUID biryaniId, BiryaniDTO biryaniDTO) {
        BiryaniDTO existing = map.get(biryaniId);

        if (StringUtils.hasText(biryaniDTO.getBiryaniName())) {
            existing.setBiryaniName(biryaniDTO.getBiryaniName());
        }
        if (biryaniDTO.getBiryaniStyle() != null) {
            existing.setBiryaniStyle(biryaniDTO.getBiryaniStyle());
        }
        if (biryaniDTO.getPrice() != null) {
            existing.setPrice(biryaniDTO.getPrice());
        }
        if (StringUtils.hasText(biryaniDTO.getBarcode())) {
            existing.setBarcode(biryaniDTO.getBarcode());
        }
        if (biryaniDTO.getQuantityOnHand() != null) {
            existing.setQuantityOnHand(biryaniDTO.getQuantityOnHand());
        }
        if (biryaniDTO.getVersion() != null) {
            existing.setVersion(biryaniDTO.getVersion());
        }
        return Optional.of(existing);

    }

    @Override
    public Optional<BiryaniDTO> getBiryaniById(UUID id) {

        log.debug("Log Debug for getting buryani by ID");
        return Optional.of(map.get(id));

    }
}
