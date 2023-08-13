package com.example.demo.controller;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/customer")
    public List<Customer> listAll(){
        return service.listAll();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> get(@PathVariable Integer id){
        try{
            Customer customer =service.findById(id);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<String> add (@RequestBody Customer customer){
        if(customer.getId()==null) {
            service.save(customer);
            return new ResponseEntity<String>("Klienti u shtua me sukses", HttpStatus.OK);
        }else return new ResponseEntity<String>("Ju nuk mund te vendosni Id klineti pasi ajo gjenerohet automatikisht",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<String> update (@RequestBody Customer customer,@PathVariable Integer id){
        if(service.isPresent(id)){
            customer.setId(id);// sepse ne database id inkrementohet sa here shtojme dhe ne duam id e vjeter
            service.save(customer);
            return new ResponseEntity<String>("Klienti u updatua me sukses",HttpStatus.OK);
        }else return new ResponseEntity<String>("Klienti me kete id nuk ekziton",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteById (@PathVariable Integer id){
        if(service.isPresent(id)){
            service.delete(id);
            return new ResponseEntity<String>("Klienti u fshi me sukses",HttpStatus.OK);
        }else return new ResponseEntity<String>("Klienti me kete id nuk u gjet",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/customer")
    public ResponseEntity<String> deleteAll(){
        service.deleteAll();
        return new ResponseEntity<String >("Klienete u fshin me sukses",HttpStatus.OK);
    }






}
