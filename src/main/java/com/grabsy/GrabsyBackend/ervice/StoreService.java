package com.grabsy.GrabsyBackend.ervice;

import com.grabsy.GrabsyBackend.controller.ProductController;
import com.grabsy.GrabsyBackend.controller.StoreController;
import com.grabsy.GrabsyBackend.entity.Store;
import com.grabsy.GrabsyBackend.exception.StoreNotFoundException;
import com.grabsy.GrabsyBackend.repository.StoreRepository;
import com.grabsy.GrabsyBackend.response.StoreResponse;
import com.grabsy.GrabsyBackend.util.EntityToResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(@Autowired StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    public EntityModel<Store> findById(String id){
        Store store = storeRepository.findById(id).orElseThrow(() -> new StoreNotFoundException(id));
        return EntityModel.of(store, linkTo(methodOn(StoreController.class).findById(id)).withSelfRel());
    }

    public CollectionModel<EntityModel<StoreResponse>> findAllById(List<String> ids) {
        List<EntityModel<StoreResponse>> storeList = storeRepository.findAllById(ids).stream().map(store -> {
            StoreResponse storeResponse = EntityToResponseMapper.storeToStoreResponseMapper(store);
            return EntityModel.of(storeResponse,
                    linkTo(methodOn(StoreController.class).findById(store.getId())).withSelfRel());
        }).toList();

        return CollectionModel.of(storeList,
                linkTo(methodOn(ProductController.class).findAllById(ids)).withSelfRel());
    }


}
