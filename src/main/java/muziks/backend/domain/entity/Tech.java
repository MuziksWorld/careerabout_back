package muziks.backend.domain.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "techs")
public class Tech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "techs_user_key",
                referencedColumnName = "id")
    private User user;

    @Column(name = "user_tech")
    private String tech;
}
