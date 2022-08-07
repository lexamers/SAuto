package Diploma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class LocaleConfig {
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");// базовое имя
        messageSource.setCacheSeconds(3600);// кэш секунд
        messageSource.setDefaultEncoding("UTF-8");// кодировка по умолчанию
        messageSource.setFallbackToSystemLocale(false);// возврат к локали системы
        return messageSource;
    }
}
