package com.algaworks.multidb.repository.redis;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.algaworks.multidb.model.redis.Product;

@Repository
public class ProductRepository {

	public static final String HASH_KEY = "Product";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public Product save(Product product) {
		redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
		
		return product;
	}
	
	public List<Product> findAll() {
		List<Product> products = redisTemplate.opsForHash().values(HASH_KEY).stream()
		   .map(object -> (Product) object)
		   .collect(Collectors.toList());
		
		return products;
	}
	
	public Product findById(Long id) {
		Product product = (Product) redisTemplate.opsForHash().get(HASH_KEY, id);
		
		if(product == null) {
			throw new IllegalArgumentException("Product not found");
		}
		
		return product;
	}
	
	public Product delete(Long id) {
		Product product = (Product) redisTemplate.opsForHash().get(HASH_KEY, id);
		
		if(product == null) {
			throw new IllegalArgumentException("Product not found");
		}
		
		redisTemplate.opsForHash().delete(HASH_KEY, id);
		
		return product;
	}
}
