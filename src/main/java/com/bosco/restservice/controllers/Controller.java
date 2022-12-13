package com.bosco.restservice.controllers;

import com.bosco.restservice.models.Product;
import com.bosco.restservice.services.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	RestService restService;

	@GetMapping("/product/{productId}/similar")
	public Product[] getSimilarProducts(@PathVariable(name = "productId") String productId) {
		return restService.getSimilarProducts(productId);
	}
}
