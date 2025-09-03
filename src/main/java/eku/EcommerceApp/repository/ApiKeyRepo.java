package eku.EcommerceApp.repository;

import eku.EcommerceApp.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepo extends JpaRepository<ApiKey,Long> {

    Optional<ApiKey> findByServiceName(String serviceName);

}
