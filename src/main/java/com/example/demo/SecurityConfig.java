package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    private static final String USER_SQL = "SELECT"
            + " user_id,"
            + " password,"
            + " true"
            + " FROM"
            + " m_user"
            + " WHERE"
            + " user_id = ?";

    private static final String ROLE_SQL = "SELECT"
            + " user_id,"
            + " role"
            + " FROM"
            + " m_user"
            + " WHERE"
            + " user_id = ?";

    //静的リソースへのセキュリティの適用をしない
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //loginページとsignupページのみ直リンク可能
        http.authorizeRequests()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/signup").permitAll()
            .antMatchers("/userList").hasAuthority("ROLE_ADMIN")
            .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated();

        //login処理の設定
        http.formLogin()
            .loginProcessingUrl("/login")
            .loginPage("/login")
            .failureUrl("/login")
            .usernameParameter("userId")
            .passwordParameter("password")
            .defaultSuccessUrl("/home", true);

        //login処理の設定
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login");
    }

    //認証処理
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(USER_SQL)
        .authoritiesByUsernameQuery(ROLE_SQL)
        .passwordEncoder(passwordEncoder());
    }
}
