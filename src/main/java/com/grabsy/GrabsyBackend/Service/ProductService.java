package com.grabsy.GrabsyBackend.Service;

import com.grabsy.GrabsyBackend.Controller.ProductController;
import com.grabsy.GrabsyBackend.Entity.Product;
import com.grabsy.GrabsyBackend.Exception.ProductNotFoundException;
import com.grabsy.GrabsyBackend.Repository.ProductRepository;
import com.grabsy.GrabsyBackend.Util.BeanReflectionUtil;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public EntityModel<Product> findById(String id) {
        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        return EntityModel.of(product, linkTo(methodOn(ProductController.class).findById(id)).withSelfRel(),
                linkTo(methodOn(ProductController.class).findAll()).withRel("products"));
    }

    public CollectionModel<EntityModel<Product>> findAllById(List<String> ids){
        List<EntityModel<Product>> productList = repository.findAllById(ids).stream().map(product ->
                EntityModel.of(product,
                        linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel())).toList();

        return CollectionModel.of(productList,
                linkTo(methodOn(ProductController.class).findAllById(ids)).withSelfRel());
    }

    public CollectionModel<EntityModel<Product>> findAll() {
        List<EntityModel<Product>> collect = repository.findAll().stream().map(prod ->
                EntityModel.of(prod, linkTo(methodOn(ProductController.class).findById(prod.getId())).
                        withSelfRel())).toList();

        return CollectionModel.of(collect, linkTo(methodOn(ProductController.class).findAll()).withSelfRel());
    }

    public EntityModel<Product> save(Product product) {
        Product createdProduct = repository.save(product);
        return EntityModel.of(createdProduct, linkTo(methodOn(ProductController.class).findById(createdProduct.
                getId())).withSelfRel());
    }

    public CollectionModel<EntityModel<Product>> saveAll(List<Product> products){
        List<EntityModel<Product>> productsCollectionAfterSelfRelation = repository.saveAll(products).
                stream().map(product -> EntityModel.of(product,
                linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel()))
                .toList();

        List<String> idList = products.stream().map(Product::getId).toList();

        return CollectionModel.of(productsCollectionAfterSelfRelation,
                linkTo(methodOn(ProductController.class).findAllById(idList)).withSelfRel());
    }

    public EntityModel<Product> findByIdAndUpdate(String id, Product productToSave) {
        return repository.findById(id).map(existingProduct -> {
            // Copy non-null fields from incoming product to the existing one
            Product updated = BeanReflectionUtil.copyNonNullFields(existingProduct, productToSave).
                    orElseGet(() -> existingProduct);

            Product saved = repository.save(updated);

            return EntityModel.of(saved, linkTo(methodOn(ProductController.class).findById(id)).
                    withSelfRel());
        }).orElseThrow(()-> new ProductNotFoundException(id));
    }

    public boolean deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }

        repository.deleteById(id);
        return true;
    }
}
