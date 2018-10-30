package ling.jiang.dynamicProxy.JDKproxy;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    10/30/2018
 * version: v1.0
 */
public class HelloWorldImpl implements HelloWorld {
    @Override
    public void sayHelloWorld() {
        System.out.println("Hello World!!!");
    }
}
