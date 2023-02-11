package muziks.backend.settingconfig;

public enum JwtConfigs {

    REFRESH_TOKEN_KEY("grknugrdfhb443"),
    ACCESS_TOKEN_KEY("sdgdfgergsd");

    private String tokenKey;
    JwtConfigs(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getKey() {
        return tokenKey;
    }
}
