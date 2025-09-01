package eku.EcommerceApp.App;


import org.springframework.stereotype.Component;

@Component("oracle")
public class Oracle implements Database{
    @Override
    public void connect() {
        System.out.println("Conncted from Oracle");
    }
}
