package com.example.crm.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class Configuration_bb {
  @Bean
   public ModelMapper getModelMapper()
   {
       return new ModelMapper();
   }
}
