package com.acloudtiger.springcloudsecurityresourceserver.controller;

import com.acloudtiger.springcloudsecurityresourceserver.model.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    Map<Integer, Account> accountMap = new HashMap<>();

    @ApiOperation(value="get account details",response=Account.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Account Details Retrieved",response=Account.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="Account not found")
    })

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