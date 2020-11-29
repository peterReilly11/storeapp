package com.store.storeapp.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Entity(name = "CartItem")
@Table(name = "cart_item")
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter
    private Long id;

    @ManyToOne(targetEntity=Cart.class, fetch = FetchType.LAZY)
    @JsonBackReference
    private @NonNull @Getter @Setter Cart cart;
    
    private @NonNull @Getter @Setter Date dateTimeCreated;
    private @NonNull @Getter @Setter String itemName;
    private @NonNull @Getter @Setter String itemDescription;
    private @NonNull @Getter @Setter String itemURL;
}