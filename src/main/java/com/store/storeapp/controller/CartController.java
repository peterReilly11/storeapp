package com.store.storeapp.controller;

import com.store.storeapp.assembler.CartModelAssembler;
import com.store.storeapp.model.Cart;
import com.store.storeapp.service.CartService;

import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Date;
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

    @GetMapping("/all-my-carts")
    public CollectionModel<EntityModel<Cart>> allCartsByEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<EntityModel<Cart>> carts = cartService.allCartsByEmailCarts(email).stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(carts, linkTo(methodOn(CartController.class).allCarts()).withSelfRel());
    }

    @GetMapping("/cart/{id}")
    public EntityModel<Cart> getCart(@PathVariable Long id) {
        Cart cart = cartService.findById(id);
        return assembler.toModel(cart);
    }

    @GetMapping("/cart")
    ResponseEntity<?> getCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        EntityModel<Cart> entityModel = assembler.toModel(cartService.findByEmailNewestCart(email));
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
            .body(entityModel);
    }

    @PutMapping("/cart")
    public ResponseEntity<?> putCart(@RequestBody Cart upsertedCart) {
        Cart updatedCart = cartService.putCart(upsertedCart);
        EntityModel<Cart> entityModel = assembler.toModel(updatedCart);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @PutMapping("/cart-checkout")
    public EntityModel<Cart> checkout(@RequestBody Cart upsertedCart) {
        //complete previous order
        upsertedCart.setDateTimePlaced(new Date());
        cartService.putCart(upsertedCart);

        //create new cart for user
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Cart cart = cartService.createCart();
        cart.setEmail(email);
        EntityModel<Cart> entityModel = assembler.toModel(cartService.putCart(cart));
        
        return entityModel;
    }

    @DeleteMapping("/cart/{id}")
    ResponseEntity<?> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
