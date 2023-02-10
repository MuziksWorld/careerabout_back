package muziks.backend.domain.signdtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignErrorDto {

    private final String errorField;
    private final String errorMessage;
}
