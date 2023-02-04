package muziks.backend.settingconfig;

public enum JwtConfigs {

    SECRET_KEY("grknugrdfhb443");

    private String secretKey;
    JwtConfigs(String secretKey) {
        this.secretKey = secretKey;
    }
}
