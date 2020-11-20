package com.example.demo.config;

import com.example.demo.repository.ReaderRepository;
import com.example.demo.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ReaderRepository readerRepository;
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").hasRole("READER")
                .and()
                .formLogin()
               // .loginPage("/readingList")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()//permitAll()
                .and()
                .csrf()
                .disable();//hasRole("READER");
    //    http.formLogin();
      //          .antMatchers("/**").hasRole("READER")

//                 .and()
//                 .csrf()
//                 .disable();
//                .loginPage("/login")
//                .failureUrl("/login?error=true");
    }
    @Autowired
    private DataSource dataSource;
    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username)
                            throws UsernameNotFoundException {
                        return readerRepository.findByUsername("abc");
                    }
                }).passwordEncoder(NoOpPasswordEncoder.getInstance());
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("abc").password(new BCryptPasswordEncoder().encode("1234")).roles("READER");
//       PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }
}
