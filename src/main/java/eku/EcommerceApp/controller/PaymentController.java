package eku.EcommerceApp.controller;

import eku.EcommerceApp.jwt.JwtUtil;
import eku.EcommerceApp.model.Orders;
import eku.EcommerceApp.model.Payment;
import eku.EcommerceApp.repository.OrdersRepo;
import eku.EcommerceApp.repository.PaymentRepo;
import eku.EcommerceApp.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/payments")
@RestController
public class PaymentController {

    @Autowired
    private PaymentService service;


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OrdersRepo orepo;


    @PostMapping("/save")
    public ResponseEntity<?> savePayment(@RequestBody Payment payment, HttpServletRequest request) {


        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String usernameFromToken = jwtUtil.extractUsername(token);

        // ✅ Pass username to service instead of trusting frontend
        Payment savedPayment = service.savePayment(payment, usernameFromToken);

        return ResponseEntity.ok(savedPayment);
    }

}
