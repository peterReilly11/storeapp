package com.store.storeapp.model;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Entity(name = "Cart")
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) @Getter @Setter
    private Long id;
    
    private @NonNull @Getter @Setter String firstName;
    private @NonNull @Getter @Setter String lastName;
    private @NonNull @Getter @Setter Date dateTimePlaced;
    private @NonNull @Getter @Setter String deliveryCity;

    @OneToMany(
        targetEntity=CartItem.class,
        mappedBy = "cart",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private @Getter @Setter List<CartItem> cartItems = new ArrayList<>();

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }
 
    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setCart(null);
    }

    public String prettyPrint(){
        return this.firstName + 
            " " + this.lastName + 
            " " + this.dateTimePlaced + 
            " " + this.deliveryCity;
    }
}