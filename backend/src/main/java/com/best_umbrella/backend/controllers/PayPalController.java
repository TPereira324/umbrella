package com.best_umbrella.backend.controllers;

import com.best_umbrella.backend.service.PayPalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/paypal")
public class PayPalController {

    private final PayPalService service;

    public PayPalController(PayPalService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> createOrder(
            @RequestParam(defaultValue = "10.00") String value,
            @RequestParam(defaultValue = "EUR") String currency
    ) {
        Map<String, Object> order = service.createOrder(value, currency);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/capture/{orderId}")
    public ResponseEntity<Map<String, Object>> captureOrder(@PathVariable String orderId) {
        Map<String, Object> captured = service.captureOrder(orderId);
        return ResponseEntity.ok(captured);
    }
}