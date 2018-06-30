package com.example.otus.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
@ComponentScan("com.example.otus")
public class ApplicationConfiguration {

    /**
     * Источник сообщений для вопросов
     *
     * @return @{@link MessageSource} для вопросов
     * */
    @Bean("quizMessageSource")
    public MessageSource quizMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/quizes");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Источник сообщений для элементов интерфейса
     *
     * @return @{@link MessageSource} интерфейса приложения
     */
    @Bean("interfaceMessageSource")
    public MessageSource interfaceMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/interface");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * В академических целях возвращается всегда английская локаль. Можно вынести настройку в properties-файл,
     * но сомнительная затея в данном случае, как мне кажется
     *
     * @return локаль, используемая в приложении
     */
    @Deprecated
    @Bean("temporalLocale")
    public Locale locale() {
        return Locale.ENGLISH;
    }
}
