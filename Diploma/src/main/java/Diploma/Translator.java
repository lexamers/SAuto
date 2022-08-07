package Diploma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.context.i18n.LocaleContextHolder;
import java.util.Locale;

     @Component
     public class Translator {

         private static ReloadableResourceBundleMessageSource messageSource; //перезагрузить источник сообщения о пакете ресурсов
    @Autowired
    Translator (ReloadableResourceBundleMessageSource messageSource){
        Translator.messageSource=messageSource;}

         public static String toLocale(String msgCode){

    Locale locale=LocaleContextHolder.getLocale();
    return messageSource.getMessage (msgCode,null,locale);}
}