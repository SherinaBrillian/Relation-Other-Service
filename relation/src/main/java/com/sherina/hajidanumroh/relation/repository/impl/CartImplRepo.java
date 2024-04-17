package com.sherina.hajidanumroh.relation.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.sherina.hajidanumroh.relation.constants.Constant;
import com.sherina.hajidanumroh.relation.dto.request.CartRequest;
import com.sherina.hajidanumroh.relation.model.CartModel;
import com.sherina.hajidanumroh.relation.repository.CartRepo;

@Service
public class CartImplRepo implements CartRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void saveData(CartRequest cartModel) {
        UUID cartUid = UUID.randomUUID();
        cartModel.setCartUid(cartUid.toString().replace("-", ""));
        Object[] queryParam = new Object[] {
            cartModel.getCartUid(), 
            cartModel.getCustomerUid(), 
            cartModel.getProductUid(), 
            cartModel.getPriceUid(),
            cartModel.getQty(),
            cartModel.getServiceUid(),
        };
        jdbcTemplate.update(Constant.Cart.INSERT, queryParam);
    }

    @Override
    public void updateData(CartRequest cartModel) {
        Object[] queryParam = new Object[] {
            cartModel.getCustomerUid(), 
            cartModel.getProductUid(), 
            cartModel.getPriceUid(),
            cartModel.getQty(),
            cartModel.getServiceUid(),
            cartModel.getCartUid(),
        };
        jdbcTemplate.update(Constant.Cart.UPDATE, queryParam);
    }

    @Override
    public void deleteData(String cartUid) {
        Object[] queryParam = new Object[] {cartUid};
        jdbcTemplate.update(Constant.Cart.DELETE, queryParam);
    }

    @Override
    public List<CartModel> getAll() {
        List<CartModel> data = jdbcTemplate.query(Constant.Cart.GET_ALL, new CartListExtractor());
        return data;
    }

    @Override
    public CartModel getById(String cartUid) {
        Object[] queryParam = new Object[] {cartUid};
        CartModel data = jdbcTemplate.query(Constant.Cart.GET_BY_ID, new CartExtractor(), queryParam);
        return data;
    }

    //EXTRACTOR
    public static final class CartListExtractor implements ResultSetExtractor<List<CartModel>> {
        @Override
        public List<CartModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<CartModel> data = new ArrayList<>();
            while (rs.next()) {
                CartModel cartModel = new CartModel();
                cartModel.setCartUid(rs.getString("cartUid"));
                cartModel.setCustomerUid(rs.getString("customerUid"));
                cartModel.setProductUid(rs.getString("productUid"));
                cartModel.setPriceUid(rs.getString("priceUid"));
                cartModel.setQty(rs.getString("qty"));
                cartModel.setServiceUid(rs.getString("serviceUid"));
                cartModel.setCreatedAt(rs.getString("createdAt"));
                cartModel.setUpdatedAt(rs.getString("updatedAt"));
                data.add(cartModel);
            }
            return data;
            
        }
    }

    public static final class CartExtractor implements ResultSetExtractor<CartModel> {
        @Override
        public CartModel extractData(ResultSet rs) throws SQLException, DataAccessException {
            CartModel cartModel = new CartModel();
            if(rs.next()){
                cartModel.setCartUid(rs.getString("cartUid"));
                cartModel.setCustomerUid(rs.getString("customerUid"));
                cartModel.setProductUid(rs.getString("productUid"));
                cartModel.setPriceUid(rs.getString("priceUid"));
                cartModel.setQty(rs.getString("qty"));
                cartModel.setServiceUid(rs.getString("serviceUid"));
                cartModel.setCreatedAt(rs.getString("createdAt"));
                cartModel.setUpdatedAt(rs.getString("updatedAt"));

                return cartModel;
            }
            else{
                cartModel = null;
                return cartModel;
            }
            
        }
    }
}
