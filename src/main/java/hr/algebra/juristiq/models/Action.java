package hr.algebra.juristiq.models;

import hr.algebra.juristiq.enums.ActionType;
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
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActionType type; // Enum defining the type of action.

    private double amount; // Cost or value associated with the action.

    private LocalDate date; // Date of the action.

    private LocalTime time; // Optional time of the action.

    private Integer deadlineDays; // Optional deadline in days.

    private String description; // Optional additional details.

    @ManyToOne
    @JoinColumn(name = "litigation_case_id")
    private LitigationCase litigationCase;
}


