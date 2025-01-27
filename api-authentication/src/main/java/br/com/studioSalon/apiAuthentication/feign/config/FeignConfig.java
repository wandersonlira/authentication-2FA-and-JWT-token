package br.com.studioSalon.apiAuthentication.feign.config;

import feign.Logger;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignConfig {

    @Bean
    public SpringMvcContract feignContract() {
        return new SpringMvcContract();
    }

    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Logger log() {
        return new Slf4jLogger();
    }
}
