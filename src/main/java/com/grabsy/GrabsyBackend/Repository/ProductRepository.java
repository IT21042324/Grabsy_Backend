package com.grabsy.GrabsyBackend.Repository;

import com.grabsy.GrabsyBackend.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
}
