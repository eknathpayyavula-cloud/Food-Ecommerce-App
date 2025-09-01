package eku.EcommerceApp.App;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    private final Database database;

    @Autowired
    public AppService(@Qualifier("oracle") Database database) {
        this.database = database;
    }

    public void run() {
        database.connect();
    }
}
