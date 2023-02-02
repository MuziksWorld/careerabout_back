package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projects_user_key")
    private User user;

    @Column(name = "projects_name")
    private String name;
    @Column(name = "projects_start_day")
    private String startDay;
    @Column(name = "projects_end_day")
    private String endDay;
    @Column(name = "projects_explanation")
    private String explanation;
    @Column(name = "projects_link")
    private String link;
}
