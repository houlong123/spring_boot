package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Spring Secutiry安全控制
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/security", "/orders", "/orders/**").permitAll()      //指定不需要认证的链接
                //.antMatchers("/security/hello").authenticated().and()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/security/login")    //需要登录时，跳转登录页面
                .permitAll().and().logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("user").password("123456").roles("USER");  //在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER。
    }
}
