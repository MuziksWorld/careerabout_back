package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "techs")
public class Tech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "techs_user_key",
                referencedColumnName = "id")
//    @Column(name = "techs_user_key", columnDefinition = "INTEGER")
    private User user;

//    @Column(name = "user_tech", columnDefinition = "VARCHAR", length = 60)
//    private String tech;


    @Column(name = "user_tech")
    private String tech;
}
