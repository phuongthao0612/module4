package com.example.thi.service.impl;

import com.example.thi.model.Product;
import com.example.thi.repository.ProductRepository;
import com.example.thi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void addNew(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getProductsByType(Integer typeId, Pageable pageable) {
        return productRepository.findByProductType(typeId, pageable);
    }

    @Override
    public Page<Product> searchProducts(String name, Integer typeId, Pageable pageable) {
        if (name != null && !name.isEmpty() && typeId != null) {
            return productRepository.findByNameContainingIgnoreCaseAndProductTypeId(name, typeId, pageable);
        } else if (name != null && !name.isEmpty()) {
            return productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (typeId != null) {
            return productRepository.findByProductType(typeId, pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }
}
