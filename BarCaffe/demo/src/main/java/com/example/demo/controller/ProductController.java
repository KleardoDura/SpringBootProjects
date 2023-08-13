package com.example.demo.controller;

import com.example.demo.models.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins="http://localhost:5173/")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> list(){
        return service.listAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id){
        try{
            Product product =service.get(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Kur te sjell nje product me nje id qe eshte i ben update
    @PostMapping("/products")
    public ResponseEntity<String> add(@RequestBody Product product){
       if(product.getId()==null) {
           service.save(product);
           return  new  ResponseEntity<String>("Produkti u shtua me sukses",HttpStatus.OK);
       }else return new ResponseEntity<String>("Nuk mund te vendosni ID per nje produkt pasi ID eshte  auto",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@RequestBody Product product,@PathVariable Integer id ){
        if(service.existsById(id)){
            product.setId(id);
            service.save(product);
            return new ResponseEntity<String>("Produkti u modifikua me sukses",HttpStatus.OK);
        }
        else return new ResponseEntity<String>("Produkt me kete id nuk u gjet ",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if(service.existsById(id)){
           service.delete(id);
            return new ResponseEntity<String>("Produkti u fshi me sukses",HttpStatus.OK);
        }
        else return new ResponseEntity<String>("Produkt me kete id nuk u gjet ",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/products")
    public ResponseEntity<?> deleteAll(){
        service.deleteAll();
        return new ResponseEntity<String>("Produket u fshine me sukses",HttpStatus.OK);
    }
}
