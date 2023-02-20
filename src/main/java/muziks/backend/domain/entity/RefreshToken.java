package muziks.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class RefreshToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REFRESH_TOKEN", nullable = false)
    private String refreshToken;

    @OneToOne(mappedBy = "refreshToken")
    private User user;


}
