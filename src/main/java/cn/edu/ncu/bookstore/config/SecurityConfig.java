package cn.edu.ncu.bookstore.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

    //自定义配置

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/index", "/", "/img/**", "/json/**",
                        "/templates/**", "/html/**", "/listBooks", "/listAll", "/bookDetails", "/search")
                .permitAll()
                .antMatchers("user/**")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                    .formLogin() //基于form表单登录验证
                    .loginPage("/login")
                    //.loginProcessingUrl("/login")
                      //  .successForwardUrl("/index.html")
                      .failureUrl("/login-error")
                        .successForwardUrl("/login-success")
                       // .defaultSuccessUrl("/index")
                    .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/index")
                    .permitAll();

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

   // 认证信息管理
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}

