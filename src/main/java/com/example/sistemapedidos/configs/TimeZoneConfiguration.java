package com.example.sistemapedidos.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class TimeZoneConfiguration {

    @Configuration
    public class TimezoneConfig {

        @PostConstruct
        public void init() {
            TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        }

    }
}
