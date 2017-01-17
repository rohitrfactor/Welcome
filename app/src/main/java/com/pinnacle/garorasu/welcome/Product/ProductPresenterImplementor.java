package com.pinnacle.garorasu.welcome.Product;

/**
 * Created by garorasu on 25/11/16.
 */

public class ProductPresenterImplementor implements ProductPresenter {
    private final ProductInteractorImplementor interactor;
    private final ProductAdapterView adapter;
    private final ProductView view;


    public ProductPresenterImplementor(ProductAdapterView view, ProductView ProductView){
        this.adapter = view;
        this.interactor = new ProductInteractorImplementor(this);
        this.view = ProductView;
    }



    @Override
    public void sendDatatoAdapter(Product product) {
        adapter.addItem(product);
    }

    public void requestProducts(){
        if(view!=null){
            view.showProgress();
        }
        interactor.requestProducts();
    }
    public void onSuccess(){
        if(view!=null){
            view.hideProgress();
        }
    }
    public void onFailure(){
        if(view!=null){
            view.hideProgress();
        }
    }
    public void startProduct(Product product){
        if(view!=null){
            view.gotoProduct(product);
        }
    }
}
