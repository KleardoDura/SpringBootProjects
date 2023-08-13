package com.example.demo.controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

@RestController
public class welcomeController{

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/welcome/{name}")
    public String welcome(@PathVariable String name){
      return "Welcome "+name;
    }

    @GetMapping("/welcome1")
    public String welcome1(@RequestParam String n,@RequestParam Integer a){
        return "Welcome "+n+" Mosha:"+a;
    }

    @PostMapping("/welcome2")
        public String welcome2(@RequestBody Person p){
            return "Emri"+p.emri+" mosha"+p.mosha;
        }

}

