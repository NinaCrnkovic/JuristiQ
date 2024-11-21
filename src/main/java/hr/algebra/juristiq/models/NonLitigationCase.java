package hr.algebra.juristiq.models;

import hr.algebra.juristiq.enums.NonLitigationCaseType;
import hr.algebra.juristiq.enums.NonLitigationActionType;
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
@EqualsAndHashCode
@Table(name = "NON_LITIGATION_CASE")
public class NonLitigationCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String internalReferenceNumber;

    private Double vps;

    @Enumerated(EnumType.STRING)
    private NonLitigationCaseType caseType; // Enum za tip predmeta

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

    @Enumerated(EnumType.STRING)
    private NonLitigationActionType actionType; // Enum za akcije vezane za izvansudske predmete

    @OneToMany(mappedBy = "nonLitigationCase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NonLitigationAction> actions;

    @OneToMany(mappedBy = "nonLitigationCase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

}


