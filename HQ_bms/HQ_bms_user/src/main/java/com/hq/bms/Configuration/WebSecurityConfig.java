package com.hq.bms.Configuration;


import com.hq.bms.authentication.JwtLoginFilter;
import com.hq.bms.authentication.JwtVerifyFilter;
import com.hq.bms.service.ISysUserService;
import com.hq.bms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Adley.yan
 */
//@Configuration
//@EnableWebSecurity      //这个注解的意思是这个类是Spring Security的配置类
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private RsaKeyProperties rsaKeyProperties;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证用户的来源
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(iSysUserService).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置SpringSecurity相关信息
     * @param http
     * @throws Exception
     */
    /*@Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  //关闭csrf
                .addFilter(new JwtLoginFilter(super.authenticationManager(),rsaKeyProperties))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    //禁用session
    }*/

    /**
     * 配置SpringSecurity相关信息
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  //关闭csrf
                .addFilter(new JwtLoginFilter(super.authenticationManager(),rsaKeyProperties))
                .authorizeRequests()
                .antMatchers("/**").hasAnyRole("USER") //角色信息
                .anyRequest()   //其它资源
                .authenticated()    //表示其它资源认证通过后
                .and()
                .addFilter(new JwtVerifyFilter(super.authenticationManager(),rsaKeyProperties))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    //禁用session
    }
}
