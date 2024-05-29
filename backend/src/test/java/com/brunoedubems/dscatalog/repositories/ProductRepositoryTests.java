package com.brunoedubems.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.brunoedubems.dscatalog.entities.Product;
import com.brunoedubems.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;
	private long exintingId;
	private long nonExintingId;
	private long countTotalProducts;
	
	
	@BeforeEach
	void setUp() throws Exception{
		exintingId = 1L;
		nonExintingId = 1000L;
		countTotalProducts =25L;
	}

	@Test
	public void saveShouldPersistWithAutoincrementIdIsNull(){
		Product product = Factory.createProduct();
		product.setId(null);

		product = repository.save(product);

		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProducts +1,product.getId());
	}


	@Test
	public void deleteShouldDeleteObjetcWhenIdExists(){
		
		repository.deleteById(1L);
		Optional<Product> result = repository.findById(exintingId);
		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void findByIdShouldReturnNonEmptyOptionalProductWhenIdExists(){
		Optional<Product> result = repository.findById(1L);
		Assertions.assertTrue(result.isPresent());
	}
	@Test
	public void findByIdShouldReturnEmptyOptionalProductWhenIdDoesNot(){
		Optional<Product> result = repository.findById(nonExintingId);
		Assertions.assertTrue(result.isEmpty());
	}
}
