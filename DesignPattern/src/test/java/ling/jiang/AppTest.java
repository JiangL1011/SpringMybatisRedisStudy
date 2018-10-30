package ling.jiang;

import ling.jiang.dynamicProxy.CGLIB.CglibProxyExample;
import ling.jiang.dynamicProxy.CGLIB.ReflectServiceImpl;
import ling.jiang.dynamicProxy.JDKproxy.HelloWorld;
import ling.jiang.dynamicProxy.JDKproxy.HelloWorldImpl;
import ling.jiang.dynamicProxy.JDKproxy.JdkProxyExample;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testJdkProxy() {
        JdkProxyExample jdk = new JdkProxyExample();
        HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
        proxy.sayHelloWorld();
    }

    @Test
    public void testCGLIBProxy() {
        CglibProxyExample cglib = new CglibProxyExample();
        ReflectServiceImpl obj = (ReflectServiceImpl) cglib.getProxy(ReflectServiceImpl.class);
        obj.sayHelloWorld();
    }
}
