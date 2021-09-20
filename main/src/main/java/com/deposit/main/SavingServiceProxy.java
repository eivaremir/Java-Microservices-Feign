package com.deposit.main;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="saving",url = "http://localhost:9002/")
public interface SavingServiceProxy extends ApiService{

}
