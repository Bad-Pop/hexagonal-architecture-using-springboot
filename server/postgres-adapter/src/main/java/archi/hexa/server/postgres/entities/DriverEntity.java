package archi.hexa.server.postgres.entities;

import archi.hexa.domain.model.Driver;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "driver")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DriverEntity {

  @Id @Include @NaturalId private String number;

  private String firstName;
  private String lastName;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime creationDate;

  @LastModifiedDate private LocalDateTime lastModificationDate;

  public static DriverEntity from(final Driver driver) {
    return DriverEntity.builder()
        .number(driver.getNumber())
        .firstName(driver.getFirstName())
        .lastName(driver.getLastName())
        .build();
  }

  public Driver toDomain() {
    return Driver.builder().number(number).firstName(firstName).lastName(lastName).build();
  }
}
