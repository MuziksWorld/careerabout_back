package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "awards")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_user_key",
            referencedColumnName = "id")
//    @Column(name = "awards_user_key", columnDefinition = "INTEGER")
    private User user;

    @Column(name = "awards_Name", columnDefinition = "VARCHAR", length = 60)
    private String name;
    @Column(name = "awards_Agency", columnDefinition = "VARCHAR", length = 60)
    private String agency;
    @Column(name = "awards_Number", columnDefinition = "VARCHAR", length = 60)
    private String number;
    @Column(name = "awards_Date", columnDefinition = "VARCHAR", length = 60)
    private String date;
    @Column(name = "awards_Content", columnDefinition = "VARCHAR", length = 60)
    private String content;
}
