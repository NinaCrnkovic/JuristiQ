package hr.algebra.juristiq.models;

import hr.algebra.juristiq.enums.NonLitigationActionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NonLitigationAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NonLitigationActionType type; // Enum for non-litigation action types

    private double amount; // Cost for the action

    private LocalDate date; // Date of action

    private LocalTime time; // Optional time

    private Integer deadlineDays; // Optional deadline in days

    private String description; // Additional details

    @ManyToOne
    @JoinColumn(name = "non_litigation_case_id")
    private NonLitigationCase nonLitigationCase; // Reference to the non-litigation case
}

