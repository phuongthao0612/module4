package com.example.thi.service;

import com.example.thi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IService<Product> {
    Page<Product> getProducts(Pageable pageable);
    Page<Product> getProductsByType(Integer typeId, Pageable pageable);

    Page<Product> searchProducts(String name, Integer typeId, Pageable pageable);
}
