package com.sherina.hajidanumroh.relation.service;

import com.sherina.hajidanumroh.relation.dto.request.CartRequest;
import com.sherina.hajidanumroh.relation.dto.response.CartDataResponse;
import com.sherina.hajidanumroh.relation.dto.response.CartListResponse;
import com.sherina.hajidanumroh.relation.dto.response.WebResponseBase;

public interface CartService {
    WebResponseBase saveData(CartRequest cartModel);
    WebResponseBase updateData(CartRequest cartModel);
    WebResponseBase deleteData(String cartUid);
    CartListResponse getAll();
    CartDataResponse getById(String cartUid);
}
