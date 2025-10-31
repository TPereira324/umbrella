package com.best_umbrella.backend.api;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PaypalConfig {

    // Alinha com application.properties: paypal.client-id, paypal.client-secret, paypal.mode
    @Value("${paypal.client-id}")
    private String clientId;
    @Value("${paypal.client-secret}")
    private String clientSecret;
    @Value("${paypal.mode:sandbox}")
    private String mode;

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    @Bean
    public APIContext apiContext() {
        // Evita solicitar token na inicialização; o SDK obtém quando necessário
        APIContext context = new APIContext(clientId, clientSecret, mode);
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }
}
