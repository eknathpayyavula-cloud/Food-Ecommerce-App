package eku.EcommerceApp.App;

import org.springframework.stereotype.Component;

@Component("mysql")
public class MySQL implements Database{
    @Override
    public void connect() {
        System.out.println("Connected From MYSQL");
    }
}
