package muziks.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Users {

    // TODO
    // 1. 유저 생성 날짜 (회원가입일 컬럼)

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String userId;
    private String userPw;
    private String userName;
    private String userEnName;
    private String userPhone;
    private String userProfileImg;
    private String userEmail;
    private String userAddress;
    private String userBirth;
    private int userResumeBool;

    @OneToMany(mappedBy = "user")
    private List<Careers> careers;
}
