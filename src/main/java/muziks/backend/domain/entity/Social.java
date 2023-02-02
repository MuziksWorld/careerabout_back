package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "socials")
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socials_user_key",
            referencedColumnName = "id")
    private User user;

    @Column(name = "socials_social")
    private String social;

    @Column(name = "socials_url")
    private String url;
}
