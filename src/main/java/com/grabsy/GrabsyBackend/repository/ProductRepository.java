package com.grabsy.GrabsyBackend.repository;

import com.grabsy.GrabsyBackend.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
}
