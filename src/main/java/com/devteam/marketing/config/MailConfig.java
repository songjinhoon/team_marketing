package com.devteam.marketing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public static JavaMailSender mailSender() {
        final Properties props = new Properties();
        props.put("mail.smtp.starttls.enable","true");

        final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername("jinhoon0319@gmail.com");
        javaMailSender.setPassword("wqbswuwaqcfbmmee");
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }

}
