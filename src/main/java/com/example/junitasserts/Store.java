package com.example.junitasserts;

import java.util.*;

public interface Store {
    Map<Integer, Product> getProducts();
    void addProduct(Product product);
    void removeProduct(int productId);
    void sellProduct(int productId, int quantity) throws InsufficientInventoryException;
}
