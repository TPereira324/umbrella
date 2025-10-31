package com.best_umbrella.backend.api;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaypalController {

    @Autowired
    private PaypalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @PostMapping("/pay")
    public String payment(@RequestParam("price") Double price,
                          @RequestParam(value = "currency", defaultValue = "EUR") String currency,
                          @RequestParam(value = "method", defaultValue = "paypal") String method,
                          @RequestParam(value = "intent", defaultValue = "sale") String intent,
                          @RequestParam(value = "description", defaultValue = "Pagamento Best Umbrella") String description) {
        try {
            String base = "http://localhost:8080/";
            Payment payment = service.createPayment(price, currency, method, intent, description,
                    base + CANCEL_URL,
                    base + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if ("approval_url".equals(link.getRel())) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/pay")
    public String paymentGet(@RequestParam("price") Double price,
                             @RequestParam(value = "currency", defaultValue = "EUR") String currency,
                             @RequestParam(value = "method", defaultValue = "paypal") String method,
                             @RequestParam(value = "intent", defaultValue = "sale") String intent,
                             @RequestParam(value = "description", defaultValue = "Pagamento Best Umbrella") String description) {
        return payment(price, currency, method, intent, description);
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        // redireciona para página estática existente
        return "redirect:/cancel.html";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            if ("approved".equalsIgnoreCase(payment.getState())) {
                // encaminha para página estática de sucesso
                return "redirect:/success.html?state=approved&paymentId=" + paymentId;
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
