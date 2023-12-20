package home.springframework.practice.spring6restmvc.entities;

import home.springframework.practice.spring6restmvc.models.BiryaniStyle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Biryani {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36,columnDefinition = "varchar",
            updatable = false, nullable = false)
    private UUID id;
    @Version
    private Integer version;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String biryaniName;

    @NotNull
    private BiryaniStyle biryaniStyle;
    @NotNull
    @NotBlank
    private String barcode;
    private Integer quantityOnHand;
    @NotNull
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
