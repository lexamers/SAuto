package Diploma.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;

public class ThymeleafConfig {
    @Bean
    public LayoutDialect layoutDialect() { // выложите диалект

        return new LayoutDialect();
    }
}
