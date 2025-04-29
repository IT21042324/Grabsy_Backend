package com.grabsy.GrabsyBackend.assembler;

import com.grabsy.GrabsyBackend.controller.user.AdminController;
import com.grabsy.GrabsyBackend.entity.users.Admin;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AdminModelAssembler implements RepresentationModelAssembler<Admin, EntityModel<Admin>> {
    @Override
    public EntityModel<Admin> toModel(Admin admin) {
        return EntityModel.of(admin,
                linkTo(methodOn(AdminController.class).getAdminById(admin.getUserId())).withSelfRel(),
                linkTo(methodOn(AdminController.class).getAllAdmins()).withRel("admins"));
    }

    @Override
    public CollectionModel<EntityModel<Admin>> toCollectionModel(Iterable<? extends Admin> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
