package com.example.product.service.impl;

import com.example.product.model.Cart;
import com.example.product.model.Product;
import com.example.product.service.ICartService;
import org.springframework.stereotype.Service;


@Service
public class CartService implements ICartService {
    @Override
    public boolean checkItemInCart(Cart cart, Product product) {
        return cart.getProducts().containsKey(product);
    }

    @Override
    public void addProductToCart(Cart cart, Product product) {
        cart.addProduct(product);
    }

    @Override
    public void updateProductQuantityInCart(Cart cart, Product product, int quantity) {
        cart.updateProductQuantity(product, quantity);
    }

    @Override
    public void removeProductFromCart(Cart cart, Product product) {
        cart.getProducts().remove(product);
    }

    @Override
    public Integer countProductQuantityInCart(Cart cart) {
        return cart.countProductQuantity();
    }

    @Override
    public Float calculateTotalPayment(Cart cart) {
        return cart.countTotalPayment();
    }
}
