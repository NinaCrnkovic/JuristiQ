package hr.algebra.juristiq.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "DOCUMENT")
public class Document {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primarni ključ

    private String fileName;

    @Lob
    private byte[] data;

    private String fileType;

    @ManyToOne
    @JoinColumn(name = "litigation_case_id", nullable = false)
    private LitigationCase litigationCase;

    @ManyToOne
    @JoinColumn(name = "non_litigation_case_id", nullable = true)
    private NonLitigationCase nonLitigationCase;

}

