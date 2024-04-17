package com.sherina.hajidanumroh.relation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherina.hajidanumroh.relation.dto.request.CartRequest;
import com.sherina.hajidanumroh.relation.dto.response.CartDataResponse;
import com.sherina.hajidanumroh.relation.dto.response.CartListResponse;
import com.sherina.hajidanumroh.relation.dto.response.WebResponseBase;
import com.sherina.hajidanumroh.relation.service.impl.CartServiceImpl;

@RestController
@RequestMapping("api/v1/Cart")
public class CartController {
    @Autowired
    CartServiceImpl cartRepo;

    @PostMapping
    public WebResponseBase saveData(@RequestBody CartRequest cartModel) {
        return cartRepo.saveData(cartModel);
    }

    @PutMapping
    @RequestMapping("/update/{cartUid}")
    public WebResponseBase updateData(@PathVariable String cartUid, @RequestBody CartRequest request) {
        request.setCartUid(cartUid);
        return cartRepo.updateData(request);  
    }

    @DeleteMapping
    @RequestMapping("/delete/{cartUid}")
    public WebResponseBase deleteData(@PathVariable String cartUid) {
        return cartRepo.deleteData(cartUid);
    }

    @GetMapping
    public CartListResponse getAll() {
        return cartRepo.getAll();
    }

    @GetMapping
    @RequestMapping("/{cartUid}")
    public CartDataResponse getById(@PathVariable String cartUid) {
        return cartRepo.getById(cartUid);
    }
}
