package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "careers")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "careers_user_key",
                referencedColumnName = "id")
    private User user;
    @Column(name = "careers_name")
    private String name;
    @Column(name = "careers_start_day")
    private String startDay;
    @Column(name = "careers_end_day")
    private String endDay;
    @Column(name = "careers_duty")
    private String duty;
    @Column(name = "careers_result")
    private String result;

}
