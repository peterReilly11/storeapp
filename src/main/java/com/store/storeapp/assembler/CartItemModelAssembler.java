package com.store.storeapp.assembler;

import com.store.storeapp.controller.CartItemController;
import com.store.storeapp.model.CartItem;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component

public class CartItemModelAssembler implements RepresentationModelAssembler<CartItem, EntityModel<CartItem>>{
    @Override
    public EntityModel<CartItem> toModel(CartItem cartItem) {

        return EntityModel.of(cartItem, //
            linkTo(methodOn(CartItemController.class).getCartItem(cartItem.getId())).withSelfRel(),
            linkTo(methodOn(CartItemController.class).allCartItems()).withRel("all-cart-items"));
    }
}
