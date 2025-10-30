package com.best_umbrella.backend.service;

import com.best_umbrella.backend.config.PayPalProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class PayPalService {

    private final PayPalProperties props;
    private final RestTemplate restTemplate = new RestTemplate();

    public PayPalService(PayPalProperties props) {
        this.props = props;
    }

    public String getAccessToken() {
        String url = props.getBaseUrl() + "/v1/oauth2/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String basic = Base64.getEncoder().encodeToString(
                (props.getClientId() + ":" + props.getClientSecret())
                        .getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + basic);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");

        ResponseEntity<Map> resp = restTemplate.postForEntity(url, new HttpEntity<>(form, headers), Map.class);
        Map body = resp.getBody();
        if (resp.getStatusCode().is2xxSuccessful() && body != null) {
            Object token = body.get("access_token");
            if (token != null) return token.toString();
        }
        throw new RuntimeException("Falha ao obter access_token do PayPal.");
    }

    public Map<String, Object> createOrder(String value, String currency) {
        String token = getAccessToken();
        String url = props.getBaseUrl() + "/v2/checkout/orders";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> amount = new HashMap<>();
        amount.put("currency_code", currency);
        amount.put("value", value);

        Map<String, Object> purchaseUnit = new HashMap<>();
        purchaseUnit.put("amount", amount);

        Map<String, Object> payload = new HashMap<>();
        payload.put("intent", "CAPTURE");
        payload.put("purchase_units", Collections.singletonList(purchaseUnit));

        try {
            ResponseEntity<Map> resp = restTemplate.postForEntity(url, new HttpEntity<>(payload, headers), Map.class);
            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                Map body = resp.getBody();
                Object id = body.get("id");
                String approveHref = null;
                Object linksObj = body.get("links");
                if (linksObj instanceof List<?> list) {
                    for (Object o : list) {
                        if (o instanceof Map<?, ?> lm) {
                            Object rel = lm.get("rel");
                            if (rel != null && "approve".equalsIgnoreCase(rel.toString())) {
                                Object href = lm.get("href");
                                if (href != null) {
                                    approveHref = href.toString();
                                    break;
                                }
                            }
                        }
                    }
                }

                Map<String, Object> result = new HashMap<>();
                result.put("orderId", id != null ? id.toString() : null);
                result.put("approveLink", approveHref);
                return result;
            }
            throw new RuntimeException("Falha ao criar ordem no PayPal.");
        } catch (RestClientResponseException e) {
            throw new RuntimeException("Erro PayPal (create): " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comunicar com PayPal (create)", e);
        }
    }

    public Map<String, Object> captureOrder(String orderId) {
        String token = getAccessToken();
        String url = props.getBaseUrl() + "/v2/checkout/orders/" + orderId + "/capture";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ResponseEntity<Map> resp = restTemplate.postForEntity(url, new HttpEntity<>(Collections.emptyMap(), headers), Map.class);
            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                return resp.getBody();
            }
            throw new RuntimeException("Falha ao capturar ordem no PayPal: " + orderId);
        } catch (RestClientResponseException e) {
            throw new RuntimeException("Erro PayPal (capture): " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comunicar com PayPal (capture)", e);
        }
    }
}