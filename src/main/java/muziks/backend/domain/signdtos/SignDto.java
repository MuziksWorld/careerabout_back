package muziks.backend.domain.signdtos;

import lombok.Data;
import muziks.backend.domain.utils.PasswordUtils;
import muziks.backend.repository.UserRepository;
import muziks.backend.service.UserService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.regex.Pattern;

@Data
public class SignDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min=4, max=20, message = "아이디는 4자 이상, 20자 이하로 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min=2, max=10, message = "이름은 2자 이상, 10자 이하로 입력해주세요.")
    private String name;

    @NotBlank(message = "휴대폰번호를 입력해주세요.")
    @Size(min=11, max=11, message = "휴대폰 번호는 11자리 숫자만 가능합니다.")
    private String phoneNumber;

    private boolean isOverlappedId;
}
