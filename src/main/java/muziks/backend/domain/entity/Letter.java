package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "letters")
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letters_user_key",
            referencedColumnName = "id")
//    @Column(name = "letters_user_key", columnDefinition = "INTEGER")
    private User user;

    @Column(name = "letters_letter", columnDefinition = "VARCHAR", length = 500)
    private String letter;

//    @Column(name = "letters_letter")
//    private String letter;
}
