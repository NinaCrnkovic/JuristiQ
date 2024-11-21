package hr.algebra.juristiq.models;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.enums.LitigationCaseType;
import hr.algebra.juristiq.enums.NonLitigationActionType;
import hr.algebra.juristiq.enums.NonLitigationCaseType;
import hr.algebra.juristiq.intefaces.CaseTypeInterface;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "TARIFF_RULE")
public class TariffRule {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActionType actionType; // Parnične radnje

    @Enumerated(EnumType.STRING)
    private NonLitigationActionType nonLitigationActionType; // Izvanparnične radnje

    @Enumerated(EnumType.STRING)
    private LitigationCaseType litigationCaseType; // Parnični postupci

    @Enumerated(EnumType.STRING)
    private NonLitigationCaseType nonLitigationCaseType; // Izvanparnični postupci

    private double baseFee; // Fiksni trošak (EUR)
    private double percentage; // Postotak VPS-a (%)


}
