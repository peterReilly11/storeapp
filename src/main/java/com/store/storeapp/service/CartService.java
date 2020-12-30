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

    public List<Cart> allCartsByEmailCarts(String email) {
        return cartRepository.findByEmail(email);
    }

	public Cart findById(Long id) {
        return cartRepository.findById(id)
            .orElseThrow(() -> new CartNotFoundException(id));
    }

    public Cart findByEmailNewestCart(String email) {
        return cartRepository.findTop1ByEmailOrderByDateTimeCreatedDesc(email);
    }

    public Cart createCart(){
        Cart cart = new Cart();
        cart.setDateTimeCreated(new Date());
        return cartRepository.save(cart);
    }

    public Cart createCart(String email){
        Cart cart = new Cart();
        cart.setDateTimeCreated(new Date());
        cart.setEmail(email);
        return cartRepository.save(cart);
    }

    public Cart putCart(Cart upsertedCart){
        return cartRepository.findById(upsertedCart.getId())
            .map(cart -> {
                cart.setEmail(upsertedCart.getEmail());
                cart.setOrderPlaced(upsertedCart.isOrderPlaced());
                cart.setDateTimePlaced(upsertedCart.getDateTimePlaced());
                cart.setDeliveryCity(upsertedCart.getDeliveryCity());
                cart.setCartItems(upsertedCart.getCartItems());
                return cartRepository.save(cart);
            })
            .orElseGet(() -> {
                upsertedCart.setId(upsertedCart.getId());
                return cartRepository.save(upsertedCart);
            });
    }

    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }
}
