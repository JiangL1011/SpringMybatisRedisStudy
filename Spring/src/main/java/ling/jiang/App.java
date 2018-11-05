package ling.jiang;

import ling.jiang.aop.RoleAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
// 默认扫描当前包
@ComponentScan(basePackages = "ling.jiang")
@EnableAspectJAutoProxy
public class App {
    @Bean
    public RoleAspect getRoleAspect() {
        return new RoleAspect();
    }
}
