package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getAll() {
        TypedQuery<Product> query = entityManager.createQuery("from Product ", Product.class);
        return query.getResultList();
    }

    @Transactional
    public boolean add(Product product) {
        try {
            entityManager.persist(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public void update(Product product) {
        entityManager.merge(product);

    }

    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void delete(int id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
    }

    public List<Product> searchByName(String name) {
        if (name == null || name.isEmpty()) {
            return getAll();
        }
        String queryString = "SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:name)";
        TypedQuery<Product> query = entityManager.createQuery(queryString, Product.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();

    }
}
