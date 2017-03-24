package com.demo.annotation;
import org.springframework.core.Ordered;  
import org.springframework.core.annotation.Order;

import com.demo.enums.UserType;

import java.lang.annotation.*;  

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD,ElementType.TYPE})  
@Documented  
//最高优先级  
@Order(Ordered.HIGHEST_PRECEDENCE)  
public @interface CheckUser {
	UserType type() default UserType.User;

}
