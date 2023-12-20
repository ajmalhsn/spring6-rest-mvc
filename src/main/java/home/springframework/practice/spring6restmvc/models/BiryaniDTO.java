package home.springframework.practice.spring6restmvc.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BiryaniDTO {
    private UUID id;
    private Integer version;
    @NotBlank
    @NotNull
    private String biryaniName;
    @NotNull
    private BiryaniStyle biryaniStyle;
    @NotNull
    @NotBlank
    private String barcode;
    private Integer quantityOnHand;
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;


}
