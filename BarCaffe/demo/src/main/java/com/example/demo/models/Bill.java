package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customer_id;
    private String customer_name;
    private Integer total;
    private Double points;

    @Transient
    /*annotation is used to mark a field to be transient for the mapping framework,
    which means the field marked with @Transient is ignored by
    mapping framework and the field not mapped to any database column (in RDBMS) or Document property (in NOSQL).
    */
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Bill(Integer id, Integer customer_id, String customer_name, Integer total, Double points, List<Product> products) {
        this.id = id;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.total = total;
        this.points = points;
        this.products = products;
    }

    public Bill(){}
}
