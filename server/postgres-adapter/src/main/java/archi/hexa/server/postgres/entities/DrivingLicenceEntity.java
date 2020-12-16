package archi.hexa.server.postgres.entities;

import archi.hexa.domain.model.DrivingLicence;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static io.vavr.collection.List.ofAll;
import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "driving_licence")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DrivingLicenceEntity {

  @Id @Include @NaturalId private String number;

  private LocalDate deliveryDate;
  private LocalDate expirationDate;
  private boolean active;
  private int points;

  @OneToOne(cascade = ALL)
  private DriverEntity driver;

  @OneToMany(cascade = ALL, orphanRemoval = true)
  private List<OffenceEntity> offences;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime creationDate;

  @LastModifiedDate private LocalDateTime lastModificationDate;

  public static DrivingLicenceEntity from(final DrivingLicence drivingLicence) {
    return DrivingLicenceEntity.builder()
        .number(drivingLicence.getNumber())
        .deliveryDate(drivingLicence.getDeliveryDate())
        .expirationDate(drivingLicence.getExpirationDate())
        .active(drivingLicence.isActive())
        .points(drivingLicence.getPoints())
        .driver(DriverEntity.from(drivingLicence.getDriver()))
        .offences(drivingLicence.getOffences().map(OffenceEntity::from).toJavaList())
        .build();
  }

  public DrivingLicence toDomain() {
    return DrivingLicence.builder()
        .number(number)
        .deliveryDate(deliveryDate)
        .expirationDate(expirationDate)
        .active(active)
        .points(points)
        .driver(driver.toDomain())
        .offences(ofAll(offences).map(OffenceEntity::toDomain))
        .build();
  }
}
