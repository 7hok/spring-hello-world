package khmerhowto.configuration;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import khmerhowto.ConfigurationService.MyUserDetailsService;

@Order(0)
@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {
    
    @Bean
    public MyUserDetailsService userDetailsService() {
      return new MyUserDetailsService();
    };

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
       http.csrf().disable().antMatcher("/**");
  
 
        http.authorizeRequests()
            .antMatchers("/admin","/admin/**")
            .hasRole("ADMIN")   
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/home", true)
            // .failureForwardUrl("/ortkertte")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/home")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .and()
            .httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userDetailsService() );
        // auth.inMemoryAuthentication().withUser("guest").password("{noop}guest1234").roles("USER");
        // auth.inMemoryAuthentication().withUser("admin").password("{noop}admin1234").roles("ADMIN");
    }
}
