package com.acloudtiger.springcloudsecurityresourceserver.controller;

import com.acloudtiger.springcloudsecurityresourceserver.model.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    Map<Integer, Account> accountMap = new HashMap<>();

    @RequestMapping("/accounts")
    public Collection<Account> getAllAccountDetails(){

        if(accountMap.isEmpty()){
            accountMap.put(1, new Account(29442, "Vijay Kumar", "test@abc.com"));
            accountMap.put(2, new Account(72864, "Vinod Kumar", "abg@sgabc.com"));
            accountMap.put(3, new Account(82744, "Albert", "tiger@dadad.com"));

        }
        return accountMap.values();
    }

}