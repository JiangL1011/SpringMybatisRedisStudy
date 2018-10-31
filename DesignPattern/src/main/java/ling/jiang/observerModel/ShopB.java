package ling.jiang.observerModel;

import java.util.Observable;
import java.util.Observer;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    10/31/2018
 * version: v1.0
 */
public class ShopB implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        String newProduct = (String) arg;
        Product p = (Product) o;
        System.out.println("ShopB收到新货：" + newProduct);
        System.out.println("ShopB货品种类：" + p.getProductList());
    }
}
