package com.sherina.hajidanumroh.relation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
     private String cartUid;
    private String customerUid;
    private String productUid;
    private String priceUid;
    private String qty;
    private String serviceUid;
    private String createdAt;
    private String updatedAt;
}
