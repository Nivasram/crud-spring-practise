package com.example.demo.exception;

public class ProductNotFoundException extends  RuntimeException{
    public ProductNotFoundException(){
        super("Product not found in the Store.");
    }
}
