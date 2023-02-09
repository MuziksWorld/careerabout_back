package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projects_user_key")
//    @Column(name = "projects_user_key", columnDefinition = "INTEGER")
    private User user;

    @Column(name = "projects_name", columnDefinition = "VARCHAR", length = 60)
    private String name;
    @Column(name = "projects_start_day", columnDefinition = "VARCHAR", length = 60)
    private String startDay;
    @Column(name = "projects_end_day", columnDefinition = "VARCHAR", length = 60)
    private String endDay;
    @Column(name = "projects_explanation", columnDefinition = "VARCHAR", length = 60)
    private String explanation;
    @Column(name = "projects_link", columnDefinition = "VARCHAR", length = 400)
    private String link;
}
