package com.example.junitasserts;

import java.util.HashMap;
import java.util.Map;

public class FooStore implements Store {
    private final Map<Integer, Product> products;

    public FooStore() {
        products = new HashMap<>();
    }

    @Override
    public Map<Integer, Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void removeProduct(int productId) {
        products.remove(productId);
    }

    @Override
    public void sellProduct(int productId, int quantity) throws InsufficientInventoryException {
        Product product = products.get(productId);
        if (product != null) {
            if (product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
            } else {
                throw new InsufficientInventoryException(
                    "Not enough inventory to sell " + Integer.toString(quantity) +
                    " units of productId " + Integer.toString(productId));
            }
        }
    }
}
