package com.grabsy.GrabsyBackend.controller.user;

import com.grabsy.GrabsyBackend.assembler.SellerModelAssembler;
import com.grabsy.GrabsyBackend.entity.users.Seller;
import com.grabsy.GrabsyBackend.service.user.SellerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public CollectionModel<EntityModel<Seller>> findAllSellers() {
        return sellerModelAssembler.toCollectionModel(sellerService.findAllSellers());
    }

    @GetMapping("{userId}")
    public EntityModel<Seller> getSellerById(@PathVariable String userId) {
        return sellerModelAssembler.toModel(sellerService.getSellerById(userId));
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<?> removeSeller(@PathVariable String userId){
        sellerService.removeSellerById(userId);

        return ResponseEntity.noContent().build();
    }

    // update
}
