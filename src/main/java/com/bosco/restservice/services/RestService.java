package com.bosco.restservice.services;

import com.bosco.restservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class RestService {

    private final RestTemplate restTemplate;
    private static final String SERVICE_URL = "http://localhost:3001";

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public Product[] getSimilarProducts(String id){
        String url = SERVICE_URL + "/product/{id}/similarids";
        try {
            String[] productIds = this.restTemplate.getForObject(url, String[].class, id);
            if (productIds == null) throw new ResponseStatusException(NOT_FOUND, "Product not found");
            Product[] productDetails = new Product[productIds.length];
            for (int i = 0; i < productIds.length; i++){
                productDetails[i] = getProductDetail(productIds[i]);
            }
            return productDetails;
        } catch (RestClientException ex){
            throw new ResponseStatusException(NOT_FOUND, "Product not found");
        }
    }

    private Product getProductDetail(String id) {
        String url = SERVICE_URL + "/product/{id}";
        return this.restTemplate.getForObject(url, Product.class, id);
    }
}