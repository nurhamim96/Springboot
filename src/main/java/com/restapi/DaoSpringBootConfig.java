package com.restapi;

import com.restapi.service.SiswaDao;
import com.restapi.service.SiswaDaoImpl;
import com.restapi.util.CommonResponseGenerator;
import com.restapi.util.CommonStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoSpringBootConfig {

    @Bean
    public SiswaDao mahasiswaDao() { return new SiswaDaoImpl(); }

    @Bean
    public CommonResponseGenerator commonResponseGenerator() { return new CommonResponseGenerator(); }

    @Bean
    public CommonStatus commonStatus() { return new CommonStatus(); }
}
