package home.springframework.practice.spring6restmvc.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Biryani {
    private UUID id;
    private Integer version;
    private String biryaniName;
    private BiryaniStyle biryaniStyle;
    private String barcode;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;


}
