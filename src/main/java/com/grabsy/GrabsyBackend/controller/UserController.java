package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.Entity.Product;
import com.grabsy.GrabsyBackend.Repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private ProductRepository productRepository;

    @GetMapping("/user/browse")
    public List<Product> browseCatalog(){
        return productRepository.findAll();
    }

    @GetMapping("/")
    public String test(){
        return "hello world";
    }
}
