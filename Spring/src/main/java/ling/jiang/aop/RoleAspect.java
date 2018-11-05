package ling.jiang.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月05日
 * version: v1.0
 */
@Aspect
@Order(1)
public class RoleAspect {
    @Pointcut("execution(* ling.jiang.service.RoleServiceImpl.printRoleInfo(..))")
    public void print() {

    }

    @Around("print()")
    public void around(ProceedingJoinPoint jp) {
        System.out.println("around before...");
        try {
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after before...");
    }

    @Before("execution(* ling.jiang.service.RoleServiceImpl.printRoleInfo(..))")
    public void before() {
        System.out.println("before...");
    }

    @After("execution(* ling.jiang.service.RoleServiceImpl.printRoleInfo(..))")
    public void after() {
        System.out.println("after...");
    }

    @AfterReturning("execution(* ling.jiang.service.RoleServiceImpl.printRoleInfo(..))")
    public void afterReturning() {
        System.out.println("afterReturning...");
    }

    @AfterThrowing("execution(* ling.jiang.service.RoleServiceImpl.printRoleInfo(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing...");
    }
}
