package hr.algebra.juristiq.models;

import hr.algebra.juristiq.enums.Court;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "LITIGATION_CASE")
public class LitigationCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;

    @Enumerated(EnumType.STRING)
    private Court court;

    private String judge;

    private Double vps;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "litigation_represented",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> representedParties = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "litigation_opposing",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> opposingParties = new ArrayList<>();

    @OneToMany(mappedBy = "litigationCase", cascade = CascadeType.ALL)

    private List<Action> actions = new ArrayList<>();

    @OneToMany(mappedBy = "litigationCase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

    // Getters and Setters
}
