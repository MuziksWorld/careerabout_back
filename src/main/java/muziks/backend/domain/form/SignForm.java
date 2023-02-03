package muziks.backend.domain.form;

import lombok.Data;

@Data
public class SignForm {

    private String id;
    private String password;
    private String name;
    private String phoneNumber;

    private boolean isOverlappedId;
}
