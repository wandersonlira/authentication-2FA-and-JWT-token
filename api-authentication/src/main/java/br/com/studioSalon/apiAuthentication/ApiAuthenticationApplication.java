package br.com.studioSalon.apiAuthentication;

import org.apache.logging.log4j.util.Base64Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@EnableFeignClients
@SpringBootApplication
public class ApiAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAuthenticationApplication.class, args);

//		Map<String, PasswordEncoder> encoders = new HashMap<>();
//		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000,
//				Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
//		encoders.put("pbkdf2", pbkdf2Encoder);
//		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
//		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
//
//		String result = passwordEncoder.encode("admin123");
//		System.out.println("My Hash: " + result);

	}

}
