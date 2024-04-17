package com.sherina.hajidanumroh.relation.repository;

import java.util.List;

import com.sherina.hajidanumroh.relation.dto.request.CartRequest;
import com.sherina.hajidanumroh.relation.model.CartModel;

public interface CartRepository {
    void saveData(CartRequest cartModel);
    void updateData(CartRequest cartModel);
    void deleteData(String cartUid);
    List<CartModel> getAll();
    CartModel getById(String cartUid);
}
