package hr.algebra.juristiq.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Korisničko ime (email)

    @Column(nullable = false)
    private String password; // Lozinka

    @OneToOne
    @JoinColumn(name = "lawyer_id", referencedColumnName = "id", unique = true)
    private Lawyer lawyer; // Povezan odvjetnik
}
