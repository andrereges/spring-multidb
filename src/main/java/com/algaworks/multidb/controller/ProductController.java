package com.algaworks.multidb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.multidb.model.redis.Product;
import com.algaworks.multidb.repository.redis.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductRepository pr;

	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product product) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(pr.save(product));
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(pr.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		try {
			return ResponseEntity
				.status(HttpStatus.OK)
				.body(pr.findById(id));
			
		} catch (IllegalArgumentException e) {
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> remove(@PathVariable Long id) {
		try {
			return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(pr.delete(id));
			
		} catch (IllegalArgumentException e) {
			
			return ResponseEntity.badRequest().build();
		}
	}
}
