package com.app.jagarv.exception.exceptions.products;


public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String error) {
        super(error);
    }
} // will be used in the /product/productId, in the /admin/products/delete/productId and will be thrown if
// the product is not found.
