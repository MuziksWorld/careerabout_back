package muziks.backend.domain.dto.logindtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginErrorDto {

    private final String fieldError;
    private final String errorMessage;
}
