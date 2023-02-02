package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Careers {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "careers_user_key",
                referencedColumnName = "id")
    private Users user;

    private String careersName;
    private String careersStartDay;
    private String careersEndDay;
    private String careersDuty;
    private String careersResult;
}
