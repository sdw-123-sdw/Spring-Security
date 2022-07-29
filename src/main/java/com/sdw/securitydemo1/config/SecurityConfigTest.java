package com.sdw.securitydemo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @ClassName SecurityConfigTest
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/4 21:54
 * @Version 1.0
 */
@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    // 配置数据源
    @Autowired
    private DataSource dataSource;
    // 配置对象
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // jdbcTokenRepository.setCreateTableOnStartup(true);   // 自动生成表
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 退出配置
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/index").permitAll();

        // 配置无权限访问跳转自定义页面
        http.exceptionHandling().accessDeniedPage("/unauth.html");

        http.formLogin()    // 自定义自己编写的登录页面
                .loginPage("/login.html")    // 登录页面设置，即登录页面地址
                .loginProcessingUrl("/user/login")        // 登录后访问的地址，即登陆成功后要跳转到哪一个controller，这个controller不需要我们去写，框架自动做到
                .defaultSuccessUrl("/test/index").permitAll()     // 登录成功后要跳转的地址
                .and().authorizeRequests()            // 定义哪些方法被保护， 哪些不被保护，即哪些可以不认证就可以访问
                .antMatchers("/", "/test/hello", "/user/login").permitAll()   //当访问"/test/hello", "/user/login"这些的时候可以不用认证
                // 1. hasAuthority 方法
                // .antMatchers("/test/index").hasAuthority("admins")   //当前登录用户，只有具有admin权限才可以访问这个路径
                // 2. hasAnyAuthority 方法
                // .antMatchers("/test/index").hasAnyAuthority("admins,manager")
                // 3. hasRole方法
                .antMatchers("/test/index").hasRole("sale")
                .anyRequest().authenticated()          // 所有请求都可以访问
                .and().rememberMe().tokenRepository(persistentTokenRepository())  // 配置记住我的操作数据库对象
                .tokenValiditySeconds(60)  // 配置token失效时间, 单位秒
                .userDetailsService(userDetailsService)
                .and().csrf().disable();       // 关闭csrf的防护方式
    }


    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}
