package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.dto.signdtos.SignDto;
import muziks.backend.domain.entity.RefreshToken;
import muziks.backend.domain.entity.User;
import muziks.backend.domain.utils.PasswordUtils;
import muziks.backend.jwt.JwtTokenProvider;
import muziks.backend.repository.JwtRepository;
import muziks.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtRepository jwtRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void save(SignDto form) {
        User user = new User();
        user.setUserId(form.getId());
        String password = form.getPassword();
        String salt = salt();
        String hashedPassword = sha512(password, salt);

        user.setSalt(salt);
        user.setPassword(hashedPassword);
        user.setName(form.getName());
        user.setPhoneNumber(form.getPhoneNumber());

        userRepository.save(user);
    }

    public void createAndSaveToken(String id) {
        User user = findById(id).get(0);
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId(), user.getRole());
        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole());
        log.info("refreshToken= {}", refreshToken);
        log.info("accessToken= {}", accessToken);
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setRefreshToken(refreshToken);
        user.setRefreshToken(refreshTokenEntity);
        jwtRepository.save(refreshTokenEntity);
        userRepository.save(user);
    }

    public List<User> findById(String id) {
        return userRepository.findById(id);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public boolean isMatches(String userId, String password) {
        //TODO
        // 어떻게 하면 null 방어적으로 깔끔하게 코드를 짤 수 있을까?
        if (userRepository.findById(userId).isEmpty()) {
            return false;
        }
        User user = userRepository.findById(userId).get(0);
        String salt = user.getSalt();

        String findPassword = sha512(password, salt);
        if (Objects.equals(user.getPassword(), findPassword)) {
            return true;
        }
        return false;
    }

    public boolean isValidPassword(String password) {
        Pattern passwordPattern = PasswordUtils.passwordPattern;
        boolean isValidPassword = passwordPattern.matcher(password).matches();
        return isValidPassword;
    }

    private String sha512(String password, String salt) {
        String saltedPassword = salt + password;
        String hashedPassword = null;

        try {
            MessageDigest hashAlgorithm = MessageDigest.getInstance("SHA-512");
            hashAlgorithm.update(saltedPassword.getBytes());
            hashedPassword = String.format("%128x", new BigInteger(1, hashAlgorithm.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    private String salt() {
        String salt = "";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[16];
            random.nextBytes(bytes);
            salt = new String(Base64.getEncoder().encode(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return salt;
    }
}
