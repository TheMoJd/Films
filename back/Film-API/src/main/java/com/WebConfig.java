package com;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/films/**")
                .allowedOrigins("http://localhost:5173") // ou utiliser allowedOriginPatterns pour des motifs plus généraux
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);

        registry.addMapping("/comments/**")
                .allowedOrigins("http://localhost:5173") // ou utiliser allowedOriginPatterns pour des motifs plus généraux
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);

        /*registry.addMapping("/users/**")
                .allowedOrigins("http://localhost:5173") // ou utiliser allowedOriginPatterns pour des motifs plus généraux
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);*/
    }
}
