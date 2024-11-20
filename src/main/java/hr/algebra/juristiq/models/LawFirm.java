package hr.algebra.juristiq.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "LAW_FIRM")
public class LawFirm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String oib;

    @Column(nullable = false)
    private String name;

    private String address;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "lawFirm", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Lawyer> lawyers = new ArrayList<>();



}
