package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(auth-> auth

 /*
                        .requestMatchers("/adminHome","/categoryManagement","/productsManagement","/ordersManagement", "/customerAllOrders",
                                "/addCategory","/viewCategories","/updateCategory/{id}","/getOne",
                                "/addProduct","/viewOneProduct/{prodId}","/viewProducts","/deleteProduct/{prodId}","/updateProduct/{prodId}",
                                "/updateOneProduct/{prodId}","/removeProductImage","/removeOneProductImage",
                                "/orderStatus/{orderId}").hasRole("ADMIN")
*/

            .requestMatchers("/category/adding","/category/getAll","/category/getOne","/category/update/{id}","/addproduct/{cateId}","/deleteproduct/{prodId}","/productAvail/{prodId}",
                    "/productNotAvail/{prodId}","/productDiscount/{prodId}","/productPrice/{prodId}","/getoneproduct/{prodId}"
                    ,"/order/{orderId}","/getAllCustomers").hasAuthority("ROLE_ADMIN")

                        .requestMatchers("/order/custid/{custId}",
                                "/getallproducts").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOMER")

                    .requestMatchers("/cart","/removeProdCart","/clearCart/{custId}"
                    ,"/getCustomer/{custId}","/updateCustomer/{custId}","/deleteCustomer/{custId}","/custAddress/{custId}","/oneAdd/{addId}",
                            "/allAdd/{custId}","/address/{addId}","/address/{addId}","/wishlist/{custId}",
                            "/{custId}/add/{prodId}","/wishlist/{custId}/remove/{prodId}","/clear/{custId}",
                            "/ordered","/order/orderid/{orderId}")
                    .hasAuthority("ROLE_CUSTOMER")

                        .requestMatchers("/addCustomer","/denied","/TokenGenerating").permitAll()
                        .anyRequest().authenticated())

                .httpBasic(hb->{})
                .exceptionHandling(eh->eh
                        .authenticationEntryPoint(customAuthenticatonEntryPoint()) // Custom Entry Point
                        //.accessDeniedPage("/denied")
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticatonEntryPoint()
    {
        return new CUstomAuthenticationEntryPoint();
        // in this class only we have given unauthorized
    }

}
