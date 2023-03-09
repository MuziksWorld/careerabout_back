package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "socials")
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socials_user_key",
            referencedColumnName = "id")
//    @Column(name = "socials_user_key", columnDefinition = "INTEGER")
    private User user;

    @Column(name = "socials_social", columnDefinition = "VARCHAR", length = 60)
    private String social;

    @Column(name = "socials_url", columnDefinition = "VARCHAR", length = 400)
    private String url;


//    @Column(name = "socials_social")
//    private String social;
//
//    @Column(name = "socials_url")
//    private String url;
}
