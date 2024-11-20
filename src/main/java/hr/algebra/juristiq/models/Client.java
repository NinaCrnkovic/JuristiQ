package hr.algebra.juristiq.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String oib;

    private String address;

    private String email;

    private String phoneNumber;

    private boolean represented; // Whether represented by the user's law firm

    @ManyToOne
    @JoinColumn(name = "lawyer_id") // Foreign key for Lawyer
    private Lawyer lawyer; // Can be null if the client is not represented

}

