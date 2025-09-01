package eku.EcommerceApp.model;

import jakarta.persistence.*;

@Entity
public class Orders {

    @Id
    private String order_id;

    private String username;
    private double total;


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Orders(String order_id, String username, double total) {
        this.order_id = order_id;
        this.username = username;
        this.total = total;
    }

    public Orders() {

    }


}

