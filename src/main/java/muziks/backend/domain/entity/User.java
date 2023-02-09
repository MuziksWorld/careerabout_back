package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    // TODO
    // 1. 유저 생성 날짜 (회원가입일 컬럼)

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "user_id", columnDefinition = "VARCHAR", length = 60)
    private String userId;

    @Column(name = "user_pw", columnDefinition = "VARCHAR", length = 200)
    private String password;

    @Column(name = "user_name", columnDefinition = "VARCHAR", length = 60)
    private String name;

    @Column(name = "user_en_name", columnDefinition = "VARCHAR", length = 60)
    private String englishName;

    @Column(name = "user_phone", columnDefinition = "VARCHAR", length = 60)
    private String phoneNumber;

    @Column(name = "user_profileImg", columnDefinition = "VARCHAR", length = 200)
    private String profileImg;

    @Column(name = "user_email", columnDefinition = "VARCHAR", length = 60)
    private String email;

    @Column(name = "user_address", columnDefinition = "VARCHAR", length = 60)
    private String address;

    @Column(name = "user_birth", columnDefinition = "VARCHAR", length = 60)
    private String birth;

    @Column(name = "user_resume_bool", columnDefinition = "INTEGER")
    private int resumeBool;

//    @Column(name = "salt", columnDefinition = "VARCHAR", length = 255)
//    private String salt;

    @OneToMany(mappedBy = "user")
    private List<Career> careers;

    @OneToMany(mappedBy = "user")
    private List<Social> socials;

    @OneToMany(mappedBy = "user")
    private List<Edu> edus;

    @OneToMany(mappedBy = "user")
    private List<Tech> teches;

    @OneToMany(mappedBy = "user")
    private List<Award> awards;

    @OneToMany(mappedBy = "user")
    private List<Project> projects;

//    private String authorization;

//    @Column(name = "salt", columnDefinition = "VARCHAR", length = 60)
//    private String role;
}
