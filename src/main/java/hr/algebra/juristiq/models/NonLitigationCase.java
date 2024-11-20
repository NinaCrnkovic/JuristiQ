package hr.algebra.juristiq.models;

import hr.algebra.juristiq.enums.NonLitigationCaseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NonLitigationCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "nonlitigation_represented",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> representedParties = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "nonlitigation_opposing",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> opposingParties = new ArrayList<>();

    private String internalReferenceNumber;

    @Enumerated(EnumType.STRING)
    private NonLitigationCaseType caseType; // Enum za tipove (npr. savjetovanje, ugovor)
}

