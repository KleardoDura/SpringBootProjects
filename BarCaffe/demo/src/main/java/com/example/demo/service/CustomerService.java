package com.example.demo.service;

import com.example.demo.models.Customer;
import com.example.demo.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repo;
    public List<Customer> listAll(){
        return repo.findAll();
    }
    public boolean isPresent(Integer id){
        return repo.existsById(id);
    }
    public Customer findById(Integer id){
        if(repo.existsById(id)){
            return repo.findById(id).get();
        }else throw new NoSuchElementException("Sorry Ska element me kete id");
    }

    public void save(Customer c){
        repo.save(c);
    }
    public void delete(Integer id){
        try{
            repo.deleteById(id);
        }catch(NoSuchElementException e){
            System.out.println(("SORRY :/ but record not found with id:" + id));
        }
    }
    public void deleteAll(){
        repo.deleteAll();
    }
}
