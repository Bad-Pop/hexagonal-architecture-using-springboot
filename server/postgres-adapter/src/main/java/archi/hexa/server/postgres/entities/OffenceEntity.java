package archi.hexa.server.postgres.entities;

import archi.hexa.domain.model.Offence;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offence")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OffenceEntity {

  @Id @Include @NaturalId private String number;

  private String cause;
  private int pointsCost;
  private LocalDate offenceDate;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime creationDate;

  @LastModifiedDate private LocalDateTime lastModificationDate;

  public static OffenceEntity from(final Offence offence) {
    return OffenceEntity.builder()
        .number(offence.getNumber())
        .cause(offence.getCause().name())
        .pointsCost(offence.getPointsCost())
        .offenceDate(offence.getOffenceDate())
        .build();
  }

  public Offence toDomain() {
    return Offence.builder()
        .number(number)
        .cause(Offence.OffenceCause.find(cause))
        .pointsCost(pointsCost)
        .offenceDate(offenceDate)
        .build();
  }
}
