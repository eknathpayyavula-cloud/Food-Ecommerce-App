package eku.EcommerceApp.service;


import eku.EcommerceApp.model.ApiKey;
import eku.EcommerceApp.repository.ApiKeyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {

    @Autowired
    private  ApiKeyRepo apiKeyRepository;

    public String getApiKey(String serviceName) {
        return apiKeyRepository.findByServiceName(serviceName)
                .map(ApiKey::getApiKey)
                .orElseThrow(() -> new RuntimeException("API Key not found for service: " + serviceName));
    }
}
