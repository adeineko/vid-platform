package be.kdg.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

   @Bean
    public LocaleResolver localeResolver() {
       SessionLocaleResolver slr = new SessionLocaleResolver();
       slr.setDefaultLocale(Locale.US);
       slr.setLocaleAttributeName("session.current.locale");
       slr.setTimeZoneAttributeName("session.current.timeZone");
       return slr;
   }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

//    @Bean("messageSource")
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
////        messageSource.setBasename("language/messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
}
