package com.pinnacle.garorasu.welcome.Product;

/**
 * Created by garorasu on 25/11/16.
 */

public interface ProductPresenter {
    void sendDatatoAdapter(Product product);
    void requestProducts();
    void startProduct(Product product);
}
