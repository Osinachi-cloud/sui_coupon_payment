package suicouponpayment.suicouponpaymentbackend.config.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Value("${allowed.credential}")
    private boolean allowedCredential;

    @Value("${allowed.origin}")
    private String allowedOrigin;

    @Value("${allowed.header}")
    private String allowedHeader;

    @Value("${allowed.method}")
    private String allowedMethod;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(allowedCredential);
        config.addAllowedOrigin(allowedOrigin);
        config.addAllowedHeader(allowedHeader);
        config.addAllowedMethod(allowedMethod);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

