package muziks.backend.domain.signdtos;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class SignErrorDtos {

    private final List<SignErrorDto> signDtos = new ArrayList<>();

    public void add(SignErrorDto signErrorDto) {
        signDtos.add(signErrorDto);
    }
}
