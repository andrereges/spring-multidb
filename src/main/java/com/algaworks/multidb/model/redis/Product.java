package com.algaworks.multidb.model.redis;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;    
}