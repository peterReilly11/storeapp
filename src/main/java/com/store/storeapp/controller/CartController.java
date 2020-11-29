package com.store.storeapp.controller;

import com.store.storeapp.assembler.CartModelAssembler;
import com.store.storeapp.model.Cart;
import com.store.storeapp.service.CartService;

import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartController {
    private CartService cartService;
    private final CartModelAssembler assembler;

    public CartController(CartService cartService, CartModelAssembler assembler){
        this.cartService = cartService;
        this.assembler = assembler;
    }

    @GetMapping("/all-carts")
    public CollectionModel<EntityModel<Cart>> allCarts() {
        List<EntityModel<Cart>> carts = cartService.allCarts().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(carts, linkTo(methodOn(CartController.class).allCarts()).withSelfRel());
    }

    @GetMapping("/cart/{id}")
    public EntityModel<Cart> getCart(@PathVariable Long id) {
        Cart cart = cartService.findById(id);
        return assembler.toModel(cart);
    }

    @PostMapping("/cart")
    ResponseEntity<?> newCart(@RequestBody Cart newCart) {
        EntityModel<Cart> entityModel = assembler.toModel(cartService.createCart(newCart));
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
            .body(entityModel);
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<?> putCart(@RequestBody Cart upsertedCart, @PathVariable Long id) {
        Cart updatedCart = cartService.putCart(upsertedCart, id);
        EntityModel<Cart> entityModel = assembler.toModel(updatedCart);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @DeleteMapping("/cart/{id}")
    ResponseEntity<?> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
