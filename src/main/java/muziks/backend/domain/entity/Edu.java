package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "edus")
public class Edu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edus_user_key",
            referencedColumnName = "id")
    private User user;

    @Column(name = "edus_name")
    private String name;
    @Column(name = "edus_number")
    private String number;
    @Column(name = "edus_school")
    private String school;
    @Column(name = "edus_start_day")
    private String startDay;
    @Column(name = "edus_end_day")
    private String endDay;
}
