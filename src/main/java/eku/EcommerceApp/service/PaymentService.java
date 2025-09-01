package eku.EcommerceApp.service;

import eku.EcommerceApp.model.Orders;
import eku.EcommerceApp.model.Payment;
import eku.EcommerceApp.repository.OrdersRepo;
import eku.EcommerceApp.repository.PaymentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo repo;


    @Autowired
    private OrdersRepo orepo;


    @Transactional
    public Payment savePayment(@RequestBody Payment payment,String username) {

        payment.setUsername(username);


        Payment p = repo.save(payment);

        if (p.getTotal() <= 0) {
            throw new IllegalArgumentException("Order total must be greater than zero");
        }

        Orders o=new Orders();
        o.setOrder_id(p.getOrder_id());
        o.setTotal(p.getTotal());
        o.setUsername(p.getUsername());
        orepo.save(o);

        return p;
    }


}
