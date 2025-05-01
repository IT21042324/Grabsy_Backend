package com.grabsy.GrabsyBackend.controller.user;

import com.grabsy.GrabsyBackend.assembler.UserModelAssembler;
import com.grabsy.GrabsyBackend.domain.SignedUser;
import com.grabsy.GrabsyBackend.dto.UpdateUserPasswordDto;
import com.grabsy.GrabsyBackend.dto.UpdateUserPhoneNumberDto;
import com.grabsy.GrabsyBackend.service.user.SignedUserService;
import com.grabsy.GrabsyBackend.service.user.factory.UserServiceFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller for the SignedUser entity, it contains endpoints related to signed users.
 */

@RestController
@RequestMapping("/signed")
public class SignedUserController {
    private final SignedUserService signedUserService;
    private final UserServiceFactory userServiceFactory;

    public SignedUserController(SignedUserService signedUserService, UserServiceFactory userServiceFactory) {
        this.signedUserService = signedUserService;
        this.userServiceFactory = userServiceFactory;
    }

    @PatchMapping("update/password")
    public <T extends SignedUser> ResponseEntity<EntityModel<T>> updatePassword(@RequestBody UpdateUserPasswordDto dto){
        T user = signedUserService.updatePassword(dto);

        UserModelAssembler<T> assembler = userServiceFactory.getModelAssembler(dto.getUserRole());
        EntityModel<T> entityModel = assembler.toModel(user);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PatchMapping("update/phone-number")
    public <T extends SignedUser> ResponseEntity<EntityModel<T>> updatePhoneNumber(@RequestBody UpdateUserPhoneNumberDto dto){
        T user = signedUserService.updatePhoneNumber(dto);

        UserModelAssembler<T> assembler = userServiceFactory.getModelAssembler(dto.getUserRole());
        EntityModel<T> entityModel = assembler.toModel(user);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // TODO :login, logout
}