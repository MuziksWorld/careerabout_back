package muziks.backend.domain.dto.jwtdtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {

    private String userId;
    private String refreshToken;
    private String accessToken;
}
