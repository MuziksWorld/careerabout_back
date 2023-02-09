package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "careers")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "careers_user_key",
            referencedColumnName = "id")
//    @Column(name = "careers_user_key", columnDefinition = "INTEGER")
    private User user;
    @Column(name = "careers_name", columnDefinition = "VARCHAR", length = 60)
    private String name;
    @Column(name = "careers_start_day", columnDefinition = "VARCHAR", length = 60)
    private String startDay;
    @Column(name = "careers_end_day", columnDefinition = "VARCHAR", length = 60)
    private String endDay;
    @Column(name = "careers_duty", columnDefinition = "VARCHAR", length = 60)
    private String duty;
    @Column(name = "careers_result", columnDefinition = "VARCHAR", length = 60)
    private String result;

}
