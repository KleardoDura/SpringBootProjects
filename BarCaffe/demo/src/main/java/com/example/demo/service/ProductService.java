package com.example.demo.service;

import com.example.demo.models.Product;
import com.example.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> listAll(){
        return repo.findAll();
    }

    public void save(Product p){
        repo.save(p);
    }

    public Product get(Integer id) {
        Optional<Product> product =repo.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else throw new NoSuchElementException("SORRY :/ but record not found with id:" + id);
    }
    public void delete(Integer id){
        try{
            repo.deleteById(id);
        }catch (NoSuchElementException e){
            System.out.println(("SORRY :/ but record not found with id:" + id));
        }
    }

    public void deleteAll(){
        repo.deleteAll();
    }
    public boolean existsById(Integer id){
        return repo.existsById(id);
    }
}
