package com.store.storeapp.assembler;

import com.store.storeapp.controller.CartController;
import com.store.storeapp.model.Cart;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CartModelAssembler implements RepresentationModelAssembler<Cart, EntityModel<Cart>>{
    @Override
    public EntityModel<Cart> toModel(Cart cart) {

        return EntityModel.of(cart, //
            linkTo(methodOn(CartController.class).getCart(cart.getId())).withSelfRel(),
            linkTo(methodOn(CartController.class).allCarts()).withRel("carts"));
    }
}
