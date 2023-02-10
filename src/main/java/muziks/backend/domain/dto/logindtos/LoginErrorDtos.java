package muziks.backend.domain.dto.logindtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class LoginErrorDtos {

    private final List<LoginErrorDto> errors = new ArrayList<>();

    public void add(LoginErrorDto loginErrorDto) {
        errors.add(loginErrorDto);
    }
}
