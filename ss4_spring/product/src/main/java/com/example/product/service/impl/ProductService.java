package com.example.product.service.impl;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public void add(Product product) {
        productRepository.add(product);

    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }
}
