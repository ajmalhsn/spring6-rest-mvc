package home.springframework.practice.spring6restmvc.entities;

import home.springframework.practice.spring6restmvc.models.BiryaniStyle;
import jakarta.persistence.*;
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
    private String biryaniName;
    private BiryaniStyle biryaniStyle;
    private String barcode;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
