package muziks.backend.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class AppConfig implements WebMvcConfigurer {

    private final BearerAuthInterceptor bearerAuthInterceptor;

    public AppConfig(BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

//    /**
//     * 인터셉터를 거칠 url을 등록한다.
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        log.info(">>> 인터셉터 등록");
//        registry.addInterceptor(bearerAuthInterceptor)
//                .excludePathPatterns("/*")
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/sign");
//    }
}
