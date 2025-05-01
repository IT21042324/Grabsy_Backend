package com.grabsy.GrabsyBackend.controller.user;

import com.grabsy.GrabsyBackend.assembler.AdminModelAssembler;
import com.grabsy.GrabsyBackend.assembler.CustomerModelAssembler;
import com.grabsy.GrabsyBackend.assembler.SellerModelAssembler;
import com.grabsy.GrabsyBackend.dto.AdminDto;
import com.grabsy.GrabsyBackend.dto.CustomerDto;
import com.grabsy.GrabsyBackend.dto.SellerDto;
import com.grabsy.GrabsyBackend.entity.users.Admin;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.entity.users.Seller;
import com.grabsy.GrabsyBackend.ervice.user.AdminService;
import com.grabsy.GrabsyBackend.ervice.user.CustomerService;
import com.grabsy.GrabsyBackend.ervice.user.SellerService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsigned-user")
public class UnsignedUserController {
    private final AdminService adminService;
    private final SellerService sellerService;
    private final CustomerService customerService;
    private final SellerModelAssembler sellerModelAssembler;
    private final CustomerModelAssembler customerModelAssembler;
    private final AdminModelAssembler adminModelAssembler;

    public UnsignedUserController(AdminService adminService, CustomerService customerService,
                                  CustomerModelAssembler customerModelAssembler, SellerService sellerService,
                                  SellerModelAssembler sellerModelAssembler, AdminModelAssembler adminModelAssembler) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.customerModelAssembler = customerModelAssembler;
        this.sellerService = sellerService;
        this.sellerModelAssembler = sellerModelAssembler;
        this.adminModelAssembler = adminModelAssembler;
    }

    @PostMapping("/register/customer")
    public ResponseEntity<EntityModel<Customer>> registerCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.registerCustomer(customerDto);

        EntityModel<Customer> entityModel = customerModelAssembler.toModel(customer);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PostMapping("/register/seller")
    public ResponseEntity<EntityModel<Seller>> registerSeller(@RequestBody SellerDto sellerDto){
        Seller seller = sellerService.registerSeller(sellerDto);

        EntityModel<Seller> entityModel = sellerModelAssembler.toModel(seller);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<EntityModel<Admin>> registerAdmin(@RequestBody AdminDto adminDto){
        Admin admin = adminService.registerAdmin(adminDto);

        EntityModel<Admin> entityModel = adminModelAssembler.toModel(admin);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}
