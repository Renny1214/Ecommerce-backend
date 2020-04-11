package com.caseStudy.caseStudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration{

    @Order(0)
    @Configuration
    public class SellerConfiguration extends WebSecurityConfigurerAdapter{
        @Autowired
        DataSource dataSource;

        @Autowired
        public void  globalSecurityConfig(AuthenticationManagerBuilder auth) throws Exception{
            auth.
                    jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery("select email,password,is_active from sellers where email=?")
                    .authoritiesByUsernameQuery("select email,password from sellers where email=?")
                    .passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.csrf().disable()
                    .authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                    .antMatchers("/api/user").permitAll()
                    .antMatchers("/api/**").permitAll()
                    .antMatchers("/products").permitAll()
                    .antMatchers("/products/**").permitAll()
                    .antMatchers("/products/addProducts").permitAll()
                    .antMatchers("/seller").permitAll()
                    .antMatchers("/seller/**").permitAll()
                    .anyRequest().authenticated()
                    .and().httpBasic();
            http.cors();
        }
    }

    @Order(1)
    @Configuration
    public class UserConfiguration extends WebSecurityConfigurerAdapter{
        @Autowired
        private DataSource dataSource;

        @Autowired
        public void globalSecurityConfig(AuthenticationManagerBuilder auth) throws Exception {
            auth.
                    jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery("select email,password,is_active from user_information where email = ?")
                    .authoritiesByUsernameQuery("select email,password from user_information where email = ?")
                    .passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.csrf().disable()
                    .authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                    .antMatchers("/api/user").permitAll()
                    .antMatchers("/api/**").permitAll()
                    .antMatchers("/products").permitAll()
                    .antMatchers("/products/**").permitAll()
                    .antMatchers("/products/addProducts").permitAll()
                    .antMatchers("/logout/**").permitAll()
                    .anyRequest().authenticated()
                    .and().httpBasic();
            http.cors();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}