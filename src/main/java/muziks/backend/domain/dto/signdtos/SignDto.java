package muziks.backend.domain.dto.signdtos;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
public class SignDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min=4, max=20, message = "아이디는 4자 이상, 20자 이하로 입력해주세요.")
    private final String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private final String password;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min=2, max=10, message = "이름은 2자 이상, 10자 이하로 입력해주세요.")
    private final String name;

    @NotBlank(message = "휴대폰번호를 입력해주세요.")
    @Size(min=11, max=11, message = "휴대폰 번호는 11자리 숫자만 가능합니다.")
    private final String phoneNumber;

    private boolean isOverlappedId;

    public User toEntity(SignDto signDto) {
        return User.builder()
                .userId(signDto.getUserId())
                .password(signDto.getPassword())
                .name(signDto.getName())
                .phoneNumber(signDto.getPhoneNumber())
                .build();
    }
}
