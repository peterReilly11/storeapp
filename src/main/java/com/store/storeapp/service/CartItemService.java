package com.store.storeapp.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.store.storeapp.exception.CartItemNotFoundException;
import com.store.storeapp.model.CartItem;
import com.store.storeapp.repository.CartItemRepository;

import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    private CartService cartService;
    private CartItemRepository cartItemRepository;

    public CartItemService(CartService cartService, CartItemRepository cartItemRepository){
        this.cartService = cartService;
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> allCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem findById(Long id) {
        return cartItemRepository.findById(id)
            .orElseThrow(() -> new CartItemNotFoundException(id));
    }

	public Collection<CartItem> findByCartId(Long id) {
		return cartItemRepository.findByCart(cartService.findById(id));
    }
    
    public CartItem putCartItem(CartItem upsertedCartItem){
        return cartItemRepository.findById(upsertedCartItem.getId())
            .map(cartItem -> {
                cartItem.setCart(upsertedCartItem.getCart());
                cartItem.setItemName(upsertedCartItem.getItemName());
                cartItem.setItemDescription(upsertedCartItem.getItemDescription());
                cartItem.setItemURL(upsertedCartItem.getItemURL());
                cartItem.setDateTimeCreated(new Date());
                return cartItemRepository.save(cartItem);
            })
            .orElseGet(() -> {
                upsertedCartItem.setId(upsertedCartItem.getId());
                return cartItemRepository.save(upsertedCartItem);
            });
    }

    public void deleteCartItem(Long id){
        cartItemRepository.deleteById(id);
    }

	public CartItem createCartItem(CartItem newCartItem) {
		return cartItemRepository.save(newCartItem);
	}
}
