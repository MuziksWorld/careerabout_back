package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Careers {

    @Id
    @GeneratedValue
    @Column(name = "careers_user_key")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    private Long careersUserKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Users user;

    private String careersName;
    private String careersStartDay;
    private String careersEndDay;
    private String careersDuty;
    private String careersResult;
}
