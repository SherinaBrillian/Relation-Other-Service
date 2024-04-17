package com.sherina.hajidanumroh.relation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.sherina.hajidanumroh.relation.constants.UrlConstant;
import com.sherina.hajidanumroh.relation.dto.CartResponseModel;
import com.sherina.hajidanumroh.relation.dto.request.CartRequest;
import com.sherina.hajidanumroh.relation.dto.response.CartDataResponse;
import com.sherina.hajidanumroh.relation.dto.response.CartListResponse;
import com.sherina.hajidanumroh.relation.dto.response.CustomerResponse;
import com.sherina.hajidanumroh.relation.dto.response.WebResponseBase;
import com.sherina.hajidanumroh.relation.model.CartModel;
import com.sherina.hajidanumroh.relation.repository.impl.CartImplRepo;
import com.sherina.hajidanumroh.relation.service.CartService;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartImplRepo cartRepo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public WebResponseBase saveData(CartRequest cartModel) {
        try {
            WebResponseBase response = new WebResponseBase();
            cartRepo.saveData(cartModel);
            response.setStatus("OK");
            return response;
        } catch(DuplicateKeyException e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cart is already exist");
        }
    }

    @Override
    public WebResponseBase updateData(CartRequest cartModel) {
        WebResponseBase response = new WebResponseBase();
        cartRepo.updateData(cartModel);
        response.setStatus("OK");
        return response;
    }

    @Override
    public WebResponseBase deleteData(String cartUid) {
        WebResponseBase response = new WebResponseBase();
        cartRepo.deleteData(cartUid);
        response.setStatus("OK");
        return response;
    }

    @Override
    public CartListResponse getAll() {
        CartListResponse response = new CartListResponse();

        List<CartResponseModel> cartResponseModelList = new ArrayList<>();
        List<CartModel> cartModels = cartRepo.getAll();

        for (final CartModel cartModel : cartModels) {
            CustomerResponse customerResp = restTemplate.getForObject(UrlConstant.Customer.GET_CUSTOMER, CustomerResponse.class, Map.of("customerUid", cartModel.getCustomerUid()));
            CartResponseModel cartResponeModel = new CartResponseModel();
            cartResponeModel.setCartUid(cartModel.getCartUid());
            cartResponeModel.setCustomer(customerResp.getData());
            cartResponeModel.setProductUid(cartModel.getProductUid());
            cartResponeModel.setPriceUid(cartModel.getPriceUid());
            cartResponeModel.setQty(cartModel.getQty());
            cartResponeModel.setServiceUid(cartModel.getServiceUid());
            cartResponseModelList.add(cartResponeModel);
        }
        response.setStatus("OK");
        response.setData(cartResponseModelList);
        return response;
    }

    @Override
    public CartDataResponse getById(String cartUid) {
        CartDataResponse response = new CartDataResponse();

        CartResponseModel cartResponseModel = new CartResponseModel();
        CartModel cartModel = cartRepo.getById(cartUid);
        if(cartModel == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Not Found!");
        }

        CustomerResponse customerResp = restTemplate.getForObject(UrlConstant.Customer.GET_CUSTOMER, CustomerResponse.class, Map.of("customerUid", cartModel.getCustomerUid()));
        cartResponseModel.setCartUid(cartModel.getCartUid());
        cartResponseModel.setCustomer(customerResp.getData());
        cartResponseModel.setProductUid(cartModel.getProductUid());
        cartResponseModel.setPriceUid(cartModel.getPriceUid());
        cartResponseModel.setQty(cartModel.getQty());
        cartResponseModel.setServiceUid(cartModel.getServiceUid());
        response.setStatus("OK");
        response.setData(cartResponseModel);
        return response;
    }
    
}
