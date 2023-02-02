package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "awards")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_user_key",
            referencedColumnName = "id")
    private User user;

    @Column(name = "awards_Name")
    private String name;
    @Column(name = "awards_Agency")
    private String agency;
    @Column(name = "awards_Number")
    private String number;
    @Column(name = "awards_Date")
    private String date;
    @Column(name = "awards_Content")
    private String content;
}
