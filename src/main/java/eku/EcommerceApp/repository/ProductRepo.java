package eku.EcommerceApp.repository;

import eku.EcommerceApp.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
}
