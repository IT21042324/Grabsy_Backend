package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.assembler.CustomerModelAssembler;
import com.grabsy.GrabsyBackend.assembler.SellerModelAssembler;
import com.grabsy.GrabsyBackend.dto.CustomerDto;
import com.grabsy.GrabsyBackend.dto.SellerDto;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.entity.users.Seller;
import com.grabsy.GrabsyBackend.repository.CustomerRepository;
import com.grabsy.GrabsyBackend.repository.SellerRepository;
import com.grabsy.GrabsyBackend.service.CustomerService;
import com.grabsy.GrabsyBackend.service.SellerService;
import com.grabsy.GrabsyBackend.service.UnsignedUserService;
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
    private final CustomerService customerService;
    private final CustomerModelAssembler customerModelAssembler;
    private final SellerService sellerService;
    private final SellerModelAssembler sellerModelAssembler;

    public UnsignedUserController(UnsignedUserService unsignedUserService, CustomerService customerService,
                                  CustomerModelAssembler customerModelAssembler, CustomerRepository customerRepository,
                                  SellerRepository sellerRepository, SellerService sellerService,
                                  SellerModelAssembler sellerModelAssembler) {
        this.customerService = customerService;
        this.customerModelAssembler = customerModelAssembler;
        this.sellerService = sellerService;
        this.sellerModelAssembler = sellerModelAssembler;
    }

    /**
     * This method handles the HTTP POST request to register a new customer.
     * @param customerDto The DTO containing the customer's information.
     * @return A ResponseEntity containing the created customer wrapped in an EntityModel.
     */
    @PostMapping("/register/customer")
    public ResponseEntity<EntityModel<Customer>> registerCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.registerCustomer(customerDto);

        EntityModel<Customer> entityModel = customerModelAssembler.toModel(customer);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * This method handles the HTTP POST request to register a new seller.
     * @param sellerDto
     * @return A ResponseEntity containing the created seller wrapped in an EntityModel.
     */
    @PostMapping("/register/seller")
    public ResponseEntity<EntityModel<Seller>> registerSeller(@RequestBody SellerDto sellerDto){
        Seller seller = sellerService.registerSeller(sellerDto);

        EntityModel<Seller> entityModel = sellerModelAssembler.toModel(seller);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}
