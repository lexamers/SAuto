package Diploma.config;

import by.ukrop.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserService userService;// служба пользователя

    @Bean
    public PasswordEncoder passwordEncoder(){//кодировщик паролей
        return new BCryptPasswordEncoder();// блок шифрования паролей в
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){ // поставщик аудентификации дао
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);// сервис сведений о пользователе
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth){// конструктор диспетчера аудентификации
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/mngProfile", "/orderVerify", "/orderDeliver", "/mngRemoveItem", "/mngUpdateQuantity", "/mngUpdateDetails").hasRole("MANAGER")
                .antMatchers("/adminProfile", "/changeUser", "/addMenuItem", "/deleteMenuItem").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/signIn").defaultSuccessUrl("/").permitAll()
                .and().logout(logout -> logout.logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/"));
    }
}
