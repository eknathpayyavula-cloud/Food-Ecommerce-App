package eku.EcommerceApp.service;

import eku.EcommerceApp.model.Orders;
import eku.EcommerceApp.model.Payment;
import eku.EcommerceApp.repository.OrdersRepo;
import eku.EcommerceApp.repository.PaymentRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepo repo;

    @Autowired
    private PaymentRepo prepo;


    public Orders saveOrder(Orders order) {


        System.out.println("We are not using this ");

        return  order;


}}

