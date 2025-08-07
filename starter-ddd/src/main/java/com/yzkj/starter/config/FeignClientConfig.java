package com.yzkj.starter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


@Configuration
public class FeignClientConfig {

    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder(new Jackson2ObjectMapperBuilder().<ObjectMapper>build());
    }

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder(new Jackson2ObjectMapperBuilder().<ObjectMapper>build());
    }
}
