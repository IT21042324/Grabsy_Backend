package com.grabsy.GrabsyBackend.assembler;

import com.grabsy.GrabsyBackend.domain.SignedUser;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public abstract class UserModelAssembler<T extends SignedUser> implements RepresentationModelAssembler<T, EntityModel<T>> {
    @Override
    public EntityModel<T> toModel(T entity) {
        return EntityModel.of(entity);
    }

    /**
     * This method converts a collection of signed user entities into a CollectionModel object.
     * @param entities The collection of signed user entities to be converted.
     * @return A CollectionModel containing the EntityModels of the signed user entities.
     */
    @Override
    public CollectionModel<EntityModel<T>> toCollectionModel(Iterable<? extends T> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
