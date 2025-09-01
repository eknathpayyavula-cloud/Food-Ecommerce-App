package eku.EcommerceApp.controller;

import eku.EcommerceApp.jwt.JwtUtil;
import eku.EcommerceApp.model.Orders;

import eku.EcommerceApp.repository.PaymentRepo;
import eku.EcommerceApp.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "http://localhost:3000")   // 👈 add this
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orepo;


    @Autowired
    private PaymentRepo prepo;


    @Autowired
    private JwtUtil jwtUtil; //

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(
            @RequestBody Orders order,
            HttpServletRequest request
    ) {
        // ✅ Extract token from Authorization header
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // remove "Bearer "
        String usernameFromToken = jwtUtil.extractUsername(token); // get username from JWT

        // ✅ Override username in request body with JWT username
        order.setUsername(usernameFromToken);

            Orders savedOrder = orepo.saveOrder(order);
            return ResponseEntity.ok(savedOrder);

}


}