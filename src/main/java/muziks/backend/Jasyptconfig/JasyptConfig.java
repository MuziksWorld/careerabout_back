package muziks.backend.jasyptconfig;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 서버 정보 암호화 하기위한 ConfigClass
 * 아래의 URL에서 encrypt decrypt 가능
 * https://www.devglan.com/online-tools/jasypt-online-encryption-decryption
 *
 * application-dev.yml 안에 있는 암호화된 DB 정보를 decrypt 하기 위해선 환경변수에서 값을 직접 입력받아야 합니다.
 *
 * - setAlgorithm(PBEWithMD5AndDES 암호화를 사용합니다.)
 * - setKeyObtentionIterations(PBE(Password-Based Encryption)를 1000번 해쉬화 한다는 의미입니다.)
 * - setPoolSize(생성할 암호화기의 풀의 크기를 설정합니다.)
 * - SetProviderName(암호화를 요청할 보안 공급자의 이름을 설정합니다. 이 공급자는 이미 등록되어 있어야 합니다.디폴트 값 SunJCE)
 * - setSaltGeneratorClassName(솔트 생성기를 설정합니다. 어떤 솔트 생성기를 사용할지)
 * - setIvGeneratorClassName(IV 생성기를 설정합니다.)
 * - setStringOutputType(String이 출력되는 형식을 설정합니다. base64, default, hexadecimal 사용 가능)
 */
@Configuration
@Slf4j
public class JasyptConfig {

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(@Value("${jasypt.encryptor.private-key-string}") String secretKey) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(secretKey);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        return encryptor;
    }
}
