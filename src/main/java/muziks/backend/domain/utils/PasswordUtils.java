package muziks.backend.domain.utils;

import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class PasswordUtils {
    // TODO
    // 로그인할 때 알파벳 소문자 대문자, 숫자, 특수문자가 잘 포함돼 있는데 false 반환되는 현상
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    public static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
}
