package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.User;
import muziks.backend.domain.form.SignDto;
import muziks.backend.domain.utils.PasswordUtils;
import muziks.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findById(String id) {
        return userRepository.findById(id);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public void sign(SignDto form) {
        User user = new User();
        user.setUserId(form.getId());
        String password = form.getPassword();
        String salt = salt();
        String hashedPassword = sha512(password, salt);

//        user.setSalt(salt);
        user.setPassword(hashedPassword);
        user.setName(form.getName());
        user.setPhoneNumber(form.getPhoneNumber());

        userRepository.save(user);
    }

    public boolean isMatches(String userId, String password) {
        //TODO
        // 어떻게 하면 null 방어적으로 깔끔하게 코드를 짤 수 있을까?
        if (userRepository.findById(userId).isEmpty()) {
            return false;
        }
        User user = userRepository.findById(userId).get(0);
//        String salt = user.getSalt();

//        String findPassword = sha512(password, salt);
//        if (Objects.equals(user.getPassword(), findPassword)) {
//            return true;
//        }
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

    public User findByAuthorization(String authorization) {
        return userRepository.findByAuthorization(authorization);
    }
}
