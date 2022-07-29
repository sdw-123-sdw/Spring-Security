package com.sdw.securitydemo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/4 19:47
 * @Version 1.0
 */
//@Configuration  // 配置类注解
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // BCryptPasswordEncoder密码加密
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encode = passwordEncoder.encode("12306");
//        System.out.println("encode = " + encode);
//        // 设置用户名，密码，角色
//        // 这里可以使用第一种直接.passwordEncoder(passwordEncoder)
//        // 也可以使用第二种，进行bean注入，交给spring进行管理
////        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("sdw").password(encode).roles("admin");
//        auth.inMemoryAuthentication().withUser("sdw").password(encode).roles("admin");
//    }
//
//    @Bean
//    PasswordEncoder password() {
//        return new BCryptPasswordEncoder();
//    }
//}
