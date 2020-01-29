package khmerhowto.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(0)
@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       http.csrf().disable().antMatcher("/**")
//           .authorizeRequests()
////           .antMatchers("/s")
////           .permitAll()
//           .anyRequest()
//           .authenticated();
//
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
       http.csrf().disable();
    //    .antMatcher("/**");
 
//        http.csrf().disable().antMatcher("/**")
 
//            .authorizeRequests()
// //           .antMatchers("/s")
// //           .permitAll()
//            .anyRequest()
//            .authenticated();
        http.authorizeRequests()
            .antMatchers("/**")
            .permitAll()
            .antMatchers("/admin/question")
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/oauth_login")
            .loginProcessingUrl("/login")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/oauth_login")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .and()
            .httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("guest").password("{noop}guest1234").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin1234").roles("ADMIN");
    }
}
