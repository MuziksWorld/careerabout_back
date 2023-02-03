package muziks.backend.domain.utils;

import java.util.regex.Pattern;

public class PasswordUtils {
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    public static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
}
