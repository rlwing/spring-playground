package com.galvanize.springplayground;

import com.galvanize.springplayground.services.WordCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public WordCounter getWordCounter(){
        return new WordCounter();
    }
}
