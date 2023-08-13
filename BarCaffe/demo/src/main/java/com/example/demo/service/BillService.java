package com.example.demo.service;
import  com.example.demo.models.Bill;
import com.example.demo.repo.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BillService {
    @Autowired
    private BillRepository repo;

    public List<Bill> listAll(){
        return  repo.findAll();
    }

    public Bill findById(Integer id){
        try{
            Bill bill=repo.findById(id).get();
            return bill;
        }catch (NoSuchElementException e){
           throw new NoSuchElementException("S ka element me kete id");
        }
    }

    public void add(Bill bill){
        repo.save(bill);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
    public void deleteAll(){
        repo.deleteAll();
    }
}
