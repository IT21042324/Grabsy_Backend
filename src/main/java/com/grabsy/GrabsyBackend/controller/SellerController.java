package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.assembler.SellerModelAssembler;
import com.grabsy.GrabsyBackend.entity.users.Seller;
import com.grabsy.GrabsyBackend.service.SellerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a controller for the Seller entity, it contains endpoints related to sellers.
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;
    private final SellerModelAssembler sellerModelAssembler;

    public SellerController(SellerService sellerService, SellerModelAssembler sellerModelAssembler) {
        this.sellerService = sellerService;
        this.sellerModelAssembler = sellerModelAssembler;
    }

    @GetMapping("{userId}")
    public EntityModel<Seller> getSellerById(String userId) {
        return sellerModelAssembler.toModel(sellerService.getSellerById(userId));
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Seller>> findAllSellers() {
        return sellerModelAssembler.toCollectionModel(sellerService.findAllSellers());
    }
}
