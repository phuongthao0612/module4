package com.example.thi.service.impl;

import com.example.thi.model.ProductType;
import com.example.thi.repository.ProductTypeRepository;
import com.example.thi.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductTypeService implements IProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;


    @Override
    public List<ProductType> getAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public void addNew(ProductType productType) {

    }

    @Override
    public void delete(ProductType productType) {

    }

    @Override
    public void update(ProductType productType) {

    }

    @Override
    public ProductType getById(int id) {
        return null;
    }
}
