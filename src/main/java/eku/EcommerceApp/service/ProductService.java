package eku.EcommerceApp.service;

import eku.EcommerceApp.model.Products;
import eku.EcommerceApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;


    @Cacheable("products")
    public List<Products> getallProducts() {
        System.out.println("Fetching products from DB...");
        return repo.findAll();
    }


    public Optional<Products> getByProductId(Long id) {
        return repo.findById(id);
    }

    // @CacheEvict(value = "products", allEntries = true)
    public void deleteById(Long id) {
         repo.deleteById(id);
    }

    @CachePut(value ="products",key = "#product.product_id")
    public Products addProduct(Products product) {
        return repo.save(product);
    }

    public List<Products> addProducts(List<Products> product) {
        return repo.saveAll(product);
    }
}
