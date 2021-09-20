package com.deposit.main;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ApiService {
    @GetMapping("/current")
    public List<Object> getCurrentAccounts();

    @GetMapping("/saving")
    public  List<Object> getSavingAccounts();
    /*
    @GetMapping("/products")
    public List<Object> getProducts();*/
}
