package ru.seurus.idioma.config.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN_LOGIN = "Admin";
    private static final String ADMIN_PASSWORD = "qazwsxedc123";

    private static final String USER_LOGIN = "User";
    private static final String USER_PASSWORD = "1234qwer";

    enum Role {
        USER,
        ADMIN;
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username(ADMIN_LOGIN).password(ADMIN_PASSWORD).roles(Role.USER.name(), Role.ADMIN.name()))
//                .withUser(userBuilder.username(USER_LOGIN).password(USER_PASSWORD).roles(Role.USER.name()));
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/**").hasAnyRole(Role.USER.name())
//                .antMatchers("/**").hasRole(Role.ADMIN.name())
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/api/v1/buy_electrics", true);
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll();
    }
}
