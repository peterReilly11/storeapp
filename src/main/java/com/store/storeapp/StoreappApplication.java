package com.store.storeapp;

/*import com.store.storeapp.model.Cart;
import com.store.storeapp.model.CartItem;

import java.util.Date;
import java.util.stream.Stream;

import com.store.storeapp.repository.CartItemRepository;
import com.store.storeapp.repository.CartRepository;
import com.store.storeapp.service.CartItemService;
import com.store.storeapp.service.CartService;

import org.springframework.boot.ApplicationRunner;*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreappApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreappApplication.class, args);
	}

	/*
	@Bean
    ApplicationRunner init(CartService cartService, CartItemService cartItemService, CartRepository cartRepository, CartItemRepository cartItemRepository) {
		Cart cart = cartService.createCart();
		cart.setDeliveryCity("Chicago");
		cart.setOrderPlaced(false);
		cart.setEmail("peter.reilly11@gmail.com");

		Stream.of("Andromeda Galaxy", "Milky Way", "Earth").forEach(name -> {
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setDateTimeCreated(new Date());
			cartItem.setItemName(name);
			cartItem.setItemDescription(name + " Description");
			cartItem.setItemURL(name + ".com");
			cart.addCartItem(cartItem);
		});
		cartService.putCart(cart);

		return args -> {
			cartRepository.findAll().forEach(System.out::println);
			cartItemRepository.findAll().forEach(System.out::println);
		};
	} */
}