package com.sherina.hajidanumroh.relation.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlConstant {
    private static String BASE_URL_CUSTOMER;

    @Autowired
    public void setBaseUrl(@Value("${config.customer}") String baseUrlCostumer) {

        BASE_URL_CUSTOMER = baseUrlCostumer;
    }


    public final static class Customer {
        private final static String BASE_URL = BASE_URL_CUSTOMER;

        public final static String GET_CUSTOMER = BASE_URL + "/{customerUid}";
    }

}
