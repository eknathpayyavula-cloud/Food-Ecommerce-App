package eku.EcommerceApp.controller;

import eku.EcommerceApp.model.Products;
import eku.EcommerceApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")   // 👈 add this
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/getall")
    public List<Products> getallProducts(){
        return service.getallProducts();
    }

    @GetMapping("/{id}")
    public Optional<Products> getByProductId(@PathVariable Long id){
        return service.getByProductId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
         service.deleteById(id);
    }

    @PostMapping("/add")
    public Products addProduct(@RequestBody Products product){
        return service.addProduct(product);
    }

    @PostMapping("/add/bulk")
    public List<Products> addProducts(@RequestBody List<Products> product) {
        return service.addProducts(product);
    }

}
