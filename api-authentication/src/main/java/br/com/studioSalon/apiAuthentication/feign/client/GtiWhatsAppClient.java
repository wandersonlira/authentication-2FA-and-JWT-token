package br.com.studioSalon.apiAuthentication.feign.client;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GtiWhatsAppClient", url = "${message.gti.url}")
public interface GtiWhatsAppClient {

    @Headers("accept: */*")
    @GetMapping("/WA/EnviarWA")
    void sendMessage(
            @RequestParam("email") String email,
            @RequestParam("token") String token,
            @RequestParam("id") String id,
            @RequestParam("mensagem") String message,
            @RequestParam("numeros") String phoneNumbers
    );
}
