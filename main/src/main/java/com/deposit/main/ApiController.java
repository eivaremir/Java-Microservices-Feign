package com.deposit.main;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableFeignClients(basePackageClasses = {CurrentServiceProxy.class,SavingServiceProxy.class})
public class ApiController implements ApiService {
    private CurrentServiceProxy currentProxy;
    private SavingServiceProxy savingProxy;

    public ApiController(CurrentServiceProxy currentProxy,SavingServiceProxy savingProxy){

        this.currentProxy = currentProxy;
        this.savingProxy = savingProxy;

        System.out.println("Proxy Iniciado");
    }


    @Override
    @GetMapping("/current")
    public List<Object> getCurrentAccounts() {
        return currentProxy.getCurrentAccounts();
    }

    @Override
    @GetMapping("/saving")
    public List<Object> getSavingAccounts() {
        return savingProxy.getSavingAccounts();
    }
}
