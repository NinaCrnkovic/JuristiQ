package hr.algebra.juristiq.models;



import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "LAWYER")
public class Lawyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String oib;
    private String address;
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "law_firm_id")
    @ToString.Exclude
    private LawFirm lawFirm;

    @OneToOne(mappedBy = "lawyer", cascade = CascadeType.ALL)
    private User user;


}
