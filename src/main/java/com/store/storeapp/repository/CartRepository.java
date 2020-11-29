package com.store.storeapp.repository;

import com.store.storeapp.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CartRepository extends JpaRepository<Cart, Long> {
    
}