package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "awards")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_user_key",
            referencedColumnName = "id")
//    @Column(name = "awards_user_key", columnDefinition = "INTEGER")
    private User user;

//    @Column(name = "awards_name", columnDefinition = "VARCHAR", length = 60)
//    private String name;
//    @Column(name = "awards_agency", columnDefinition = "VARCHAR", length = 60)
//    private String agency;
//    @Column(name = "awards_number", columnDefinition = "VARCHAR", length = 60)
//    private String number;
//    @Column(name = "awards_date", columnDefinition = "VARCHAR", length = 60)
//    private String date;
//    @Column(name = "awards_content", columnDefinition = "VARCHAR", length = 60)
//    private String content;

    @Column(name = "awards_name")
    private String name;
    @Column(name = "awards_agency")
    private String agency;
    @Column(name = "awards_number")
    private String number;
    @Column(name = "awards_date")
    private String date;
    @Column(name = "awards_content")
    private String content;
}
