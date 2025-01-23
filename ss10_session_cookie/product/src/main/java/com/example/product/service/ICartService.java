package com.example.product.service;

import com.example.product.model.Cart;
import com.example.product.model.Product;

public interface ICartService {
    boolean checkItemInCart(Cart cart, Product product);

    void addProductToCart(Cart cart, Product product);
    void updateProductQuantityInCart(Cart cart, Product product, int quantity);

    void removeProductFromCart(Cart cart, Product product);

    Integer countProductQuantityInCart(Cart cart);
    Float calculateTotalPayment(Cart cart);
}
