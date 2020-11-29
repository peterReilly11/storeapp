package com.store.storeapp.repository;

import java.util.List;

import com.store.storeapp.model.Cart;
import com.store.storeapp.model.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
}