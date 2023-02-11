package muziks.backend;


import muziks.backend.settingconfig.JwtConfigs;
import org.junit.jupiter.api.Test;

public class JwtTest {

    @Test
    void keyTest() {
        System.out.println("accessTokenKey = " + JwtConfigs.REFRESH_TOKEN_KEY.getKey());
    }
}
