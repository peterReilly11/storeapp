package com.store.storeapp.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.store.storeapp.assembler.CartItemModelAssembler;
import com.store.storeapp.model.CartItem;
import com.store.storeapp.service.CartItemService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemController {
    private CartItemService cartItemService;
    private final CartItemModelAssembler assembler;
    
    public CartItemController(CartItemService cartItemService, CartItemModelAssembler assembler){
        this.cartItemService = cartItemService;
        this.assembler = assembler;

    }

    @PostMapping("/cart-item")
    ResponseEntity<?> newCartItem(@RequestBody CartItem newCartItem) {
        EntityModel<CartItem> entityModel = assembler.toModel(cartItemService.createCartItem(newCartItem));
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
            .body(entityModel);
    }

    @PutMapping("/cart-item")
    public ResponseEntity<?> putCartItem(@RequestBody CartItem upsertedCartItem) {
        CartItem updatedCartItem = cartItemService.putCartItem(upsertedCartItem);
        EntityModel<CartItem> entityModel = assembler.toModel(updatedCartItem);

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @GetMapping("/all-cart-items")
    public CollectionModel<EntityModel<CartItem>> allCartItems() {
        List<EntityModel<CartItem>> cartItems = cartItemService.allCartItems().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(cartItems, linkTo(methodOn(CartItemController.class).allCartItems()).withSelfRel());
    }

    @GetMapping("/cart-items/{id}")
    public CollectionModel<EntityModel<CartItem>> getCartItemsByCart(@PathVariable Long id) {
        List<EntityModel<CartItem>> cartItems = cartItemService.findByCartId(id).stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(cartItems, linkTo(methodOn(CartItemController.class).allCartItems()).withSelfRel());
    }

    @GetMapping("/cart-item/{id}")
    public EntityModel<CartItem> getCartItem(@PathVariable Long id) {
        CartItem cartItem = cartItemService.findById(id);
        return assembler.toModel(cartItem);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/cart-item/{id}")
    ResponseEntity<?> deleteCartItem(@PathVariable Long id) {
        System.out.println("deleting item " + id);
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}