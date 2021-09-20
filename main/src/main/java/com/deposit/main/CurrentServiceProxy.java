package com.deposit.main;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="current",url = "http://localhost:9001/")
public interface CurrentServiceProxy extends ApiService{

}
