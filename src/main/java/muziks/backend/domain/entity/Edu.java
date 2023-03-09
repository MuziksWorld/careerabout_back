package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "edus")
public class Edu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edus_user_key",
            referencedColumnName = "id")
//    @Column(name = "edus_user_key", columnDefinition = "INTEGER")
    private User user;

    @Column(name = "edus_name", columnDefinition = "VARCHAR", length = 60)
    private String name;
    @Column(name = "edus_number", columnDefinition = "VARCHAR", length = 60)
    private String number;
    @Column(name = "edus_school", columnDefinition = "VARCHAR", length = 60)
    private String school;
    @Column(name = "edus_start_day", columnDefinition = "VARCHAR", length = 60)
    private String startDay;
    @Column(name = "edus_end_day", columnDefinition = "VARCHAR", length = 60)
    private String endDay;


//    @Column(name = "edus_name")
//    private String name;
//    @Column(name = "edus_number")
//    private String number;
//    @Column(name = "edus_school")
//    private String school;
//    @Column(name = "edus_start_day")
//    private String startDay;
//    @Column(name = "edus_end_day")
//    private String endDay;
}
