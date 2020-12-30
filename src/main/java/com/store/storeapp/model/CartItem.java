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
    private @Getter @Setter Cart cart;
    
    private @Getter @Setter Date dateTimeCreated;
    private @Getter @Setter String itemName;
    private @Getter @Setter String itemDescription;
    private @Getter @Setter String itemURL;
}