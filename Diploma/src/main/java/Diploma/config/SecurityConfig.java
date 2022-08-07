package Diploma.config;

import Diploma.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;// служба пользователя

    @Bean
    public DaoAuthenticationProvider authProvider(){ // поставщик аудентификации дао
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);// сервис сведений о пользователе
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    private PasswordEncoder passwordEncoder() {
        return null;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth){// конструктор диспетчера аудентификации
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/mngProfile", "/reguestVerify", "/reguestDeliver", "/mngRemoveItem", "/mngUpdateDetails").hasRole("MANAGER")
                .antMatchers("/adminProfile", "/changeUser").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/signIn").defaultSuccessUrl("/").permitAll()
                .and().logout(logout -> logout.logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/"));
    }
}
