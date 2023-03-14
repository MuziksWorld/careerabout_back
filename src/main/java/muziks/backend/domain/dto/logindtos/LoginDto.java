package muziks.backend.domain.dto.logindtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {

    private String userId;
    private String password;
}
