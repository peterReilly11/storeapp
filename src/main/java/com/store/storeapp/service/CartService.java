package com.store.storeapp.service;

import java.util.Date;
import java.util.List;

import com.store.storeapp.exception.CartNotFoundException;
import com.store.storeapp.model.Cart;
import com.store.storeapp.repository.CartRepository;

import org.springframework.stereotype.Service;

@Service
public class CartService {
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public List<Cart> allCarts() {
        return cartRepository.findAll();
    }

	public Cart findById(Long id) {
        return cartRepository.findById(id)
            .orElseThrow(() -> new CartNotFoundException(id));
    }

    public Cart createCart(Cart newCart){
        return cartRepository.save(newCart);
    }

    public Cart putCart(Cart upsertedCart, Long id){
        return cartRepository.findById(id)
            .map(cart -> {
                cart.setFirstName(upsertedCart.getFirstName());
                cart.setLastName(upsertedCart.getLastName());
                cart.setDeliveryCity(upsertedCart.getDeliveryCity());
                cart.setCartItems(upsertedCart.getCartItems());
                cart.setDateTimePlaced(new Date());
                return cartRepository.save(cart);
            })
            .orElseGet(() -> {
                upsertedCart.setId(id);
                return cartRepository.save(upsertedCart);
            });
    }

    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }
}
