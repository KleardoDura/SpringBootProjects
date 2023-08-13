package com.example.demo.controller;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.service.BillService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Bill;

//import javax.persistence.criteria.CriteriaBuilder;
import java.util.Currency;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BillController {
    @Autowired
    private BillService service;

    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @GetMapping("/bills")
    public List<Bill> listAll(){
        return service.listAll();
    }

    @GetMapping("/bills/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try{
            Bill bill= service.findById(id);
            return new ResponseEntity<Bill>(bill,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<String >("S ka element me kete id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bills")
    public ResponseEntity<String> add(@RequestBody Bill bill){
        //kontrollojm nese klienti ekziston:
        if(! customerService.isPresent(  bill.getCustomer_id()))
            return new ResponseEntity<String >("Klienti me id " +  bill.getCustomer_id()+"nuk gjendet ne databaze",HttpStatus.NOT_FOUND);

        //Punojme me produktet
        int totali=0;
        for(int i=0;i<bill.getProducts().size();i++){
            //Kontrollojme nese ky produkt ekziston ne db
            if(! productService.existsById( bill.getProducts().get(i).getId()))
                return new ResponseEntity<String >("Produkti me id "+bill.getProducts().get(i).getId()+" nuk gjendet ne db",HttpStatus.NOT_FOUND);

            //MArrim produktin nga databaza
            Product updateProduct=productService.get( bill.getProducts().get(i).getId() );

            Product product=bill.getProducts().get(i);
            //Gjejme koston e ketij produkti
            totali+=product.getSasia()*updateProduct.getPrice();

            //Kapim id
            updateProduct.setId(updateProduct.getId());
            //ulim sasine
            updateProduct.setSasia( updateProduct.getSasia()-product.getSasia());

            //i ruajme ndryshimet
            productService.save(updateProduct);

        }

        //Punojme me klinetin
        //Klineti ne fature
        Customer customer = customerService.findById( bill.getCustomer_id() );
        customer.setPoints( customer.getPoints()+ (totali/10.00) );

        customer.setId(bill.getCustomer_id());
        customerService.save(customer);

        bill.setCustomer_name( customer.getName());
        bill.setTotal(totali);
        bill.setPoints(totali/10.00);
        service.add(bill);

        return new ResponseEntity<String>("Fatura u shtua me suksesc Totali :"+totali,HttpStatus.OK);
    }


}











