package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.entity.Store;
import com.grabsy.GrabsyBackend.service.StoreService;
import org.springframework.hateoas.EntityModel;

public class StoreController {
    private final StoreService service;
    public StoreController(StoreService service){
        this.service = service;
    }

    public EntityModel<Store> findById(String id){
        return service.findById(id);
    }
}
