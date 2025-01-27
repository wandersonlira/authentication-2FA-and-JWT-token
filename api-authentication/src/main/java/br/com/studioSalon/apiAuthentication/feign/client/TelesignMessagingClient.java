package br.com.studioSalon.apiAuthentication.feign.client;


import br.com.studioSalon.apiAuthentication.feign.config.TelesignClientConfig;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "telesignClient",
        url = "${message.telesign.url.sms}",
        configuration = TelesignClientConfig.class
)
public interface TelesignMessagingClient {
    @PostMapping(value = "/messaging")
    @Headers("Content-Type: application/x-www-form-urlencoded; accept: application/json ; charset=utf-8")
    void sendSMS(String requestBody);
}
