package ling.jiang.observerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    10/31/2018
 * version: v1.0
 */
public class Product extends Observable {
    private List<String> productList = null;

    private static Product instance;

    private Product(){}

    public static Product getInstance(){
        if (instance == null){
            instance = new Product();
            instance.productList = new ArrayList<>();
        }
        return instance;
    }

    public List<String> getProductList() {
        return productList;
    }

    /**
     * 增加观察者
     * @param observer 实现Observer接口的对象
     */
    public void addProductObserver(Observer observer){
        this.addObserver(observer);
    }

    public void addProduct(String newProduct){
        productList.add(newProduct);
        System.out.println("新增产品："+newProduct);
        this.setChanged();
        this.notifyObservers(newProduct);
    }
}
