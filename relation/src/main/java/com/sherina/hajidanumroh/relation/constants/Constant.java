package com.sherina.hajidanumroh.relation.constants;

public class Constant {
    public static final class Cart {
        public static final String INSERT = "Insert into tbCart (cartUid, customerUid, productUid, priceUid, qty, serviceUid) value (?,?,?,?,?,?)";
        public static final String UPDATE = "Update tbCart set  customerUid =?, productUid = ?, priceUid = ?, qty = ?, serviceUid = ? where cartUid = ?";
        public static final String DELETE = "Delete from tbCart where cartUid = ?";
        public static final String GET_ALL = "SELECT c.cartUid, c.customerUid, c.productUid, c.priceUid, c. qty, c. serviceUid, cu.customerUid AS customerUid, cu.userUid, cu.address, cu.pihkKbihu, cu.notes, cu.verifiedBy, cu.verifiedAt, cu.userImage, cu.verifyImage, cu.dirImage, cu.createdBy FROM tbCart c LEFT JOIN tbCustomer cu ON c.customerUid = cu.customerUid";
        public static final String GET_BY_ID = "SELECT c.cartUid, c.customerUid, c.productUid, c.priceUid, c. qty, c. serviceUid, cu.customerUid AS customerUid, cu.userUid, cu.address, cu.pihkKbihu, cu.notes, cu.verifiedBy, cu.verifiedAt, cu.userImage, cu.verifyImage, cu.dirImage, cu.createdBy FROM tbCart c LEFT JOIN tbCustomer cu ON c.customerUid = cu.customerUid where cartUid = ?";
    }
}
