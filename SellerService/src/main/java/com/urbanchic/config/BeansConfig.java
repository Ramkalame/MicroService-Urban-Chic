package com.urbanchic.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeansConfig {

    @Bean
    public Cloudinary cloudinary(){
        Map<String,Object> config = new HashMap<>();
        config.put("cloud_name",CloudinaryConfigProperties.CLOUD_NAME);
        config.put("api_key", CloudinaryConfigProperties.API_KEY);
        config.put("api_secret", CloudinaryConfigProperties.API_SECRETE);
        config.put("secure", true);
        return  new Cloudinary(config);
    }

}
