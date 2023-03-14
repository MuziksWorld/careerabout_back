package muziks.backend.domain.dto.signdtos;

import lombok.*;
import muziks.backend.domain.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min=4, max=20, message = "아이디는 4자 이상, 20자 이하로 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min=2, max=10, message = "이름은 2자 이상, 10자 이하로 입력해주세요.")
    private String userName;

    @NotBlank(message = "휴대폰번호를 입력해주세요.")
    @Size(min=11, max=11, message = "휴대폰 번호는 11자리 숫자만 가능합니다.")
    private String phoneNumber;

    private boolean isOverlappedId;

    public User toEntity(SignDto signDto) {
        return User.builder()
                .userId(signDto.getUserId())
                .password(signDto.getPassword())
                .name(signDto.getUserName())
                .phoneNumber(signDto.getPhoneNumber())
                .build();
    }
}
