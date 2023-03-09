package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.lang.annotation.Target;

@Entity
@Data
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "REFRESH_TOKEN", nullable = false)
    private String refreshToken;

    @OneToOne(mappedBy = "refreshToken")
    private User user;
}
