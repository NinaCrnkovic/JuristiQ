package hr.algebra.juristiq.models;

import hr.algebra.juristiq.enums.Court;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import hr.algebra.juristiq.enums.Court;
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
public class LitigationCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "litigation_represented",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> representedParties = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "litigation_opposing",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> opposingParties = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Court court;

    private String judge;

    private String designation; // Broj predmeta

    private double valueInDispute; // Vrijednost u sporu
}



