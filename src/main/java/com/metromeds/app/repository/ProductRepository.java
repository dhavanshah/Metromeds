package com.metromeds.app.repository;

import com.metromeds.app.models.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {
}