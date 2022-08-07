package Diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Locale;

@SpringBootApplication (exclude=HibernateJpaAutoConfiguration.class)
public class Application implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {// реестр перехватчиков
        interceptorRegistry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {// перехват изменения локального значения
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");

        return localeChangeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();// преобразование локали файлов  cookie
        Locale defaultLocale = new Locale("ru");
        clr.setDefaultLocale(defaultLocale);
        clr.setCookieName("lang");
        return clr;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
