package br.com.studioSalon.apiAuthentication.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;


@Configuration
public class TelesignClientConfig {
    @Value("${message.telesign.user.customerId}")
    private String apiKey;

    @Value("${message.telesign.user.apiKey}")
    private String apiSecret;


    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String credentials = apiSecret + ":" + apiKey;
                String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

                requestTemplate.header("accept", "application/json");
                requestTemplate.header("content-type", "application/x-www-form-urlencoded");
                requestTemplate.header("Authorization", "Basic " + base64Credentials);
            }
        };
    }

}
