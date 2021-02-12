package com.store.storeapp.repository;

import java.util.List;

import com.store.storeapp.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findTop1ByEmailOrderByDateTimeCreatedDesc(String email);
    List<Cart> findByEmail(String email);
}