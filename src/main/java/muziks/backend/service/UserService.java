package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.User;
import muziks.backend.domain.form.SignForm;
import muziks.backend.repository.UserRepository;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;


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

    public void sign(SignForm form) {
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
