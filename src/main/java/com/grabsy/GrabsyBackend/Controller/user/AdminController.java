package com.grabsy.GrabsyBackend.controller.user;

import com.grabsy.GrabsyBackend.assembler.AdminModelAssembler;
import com.grabsy.GrabsyBackend.entity.users.Admin;
import com.grabsy.GrabsyBackend.service.user.AdminService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminModelAssembler adminModelAssembler;

    public AdminController(AdminService adminService, AdminModelAssembler adminModelAssembler) {
        this.adminService = adminService;
        this.adminModelAssembler = adminModelAssembler;
    }

    //get
    @GetMapping("{userId}")
    public EntityModel<Admin> getAdminById(@PathVariable String userId){
        return adminModelAssembler.toModel(adminService.getAdminById(userId));
    }

    //get all
    @GetMapping("/all")
    public CollectionModel<EntityModel<Admin>> getAllAdmins(){
        return adminModelAssembler.toCollectionModel(adminService.getAllAdmins());
    }

    //delete
    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<?> removeAdminById(@PathVariable String userId){
        adminService.removeAdminById(userId);

        return ResponseEntity.noContent().build();
    }

    //update
}
