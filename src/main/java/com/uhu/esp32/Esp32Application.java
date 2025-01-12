package com.uhu.esp32;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Esp32Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Esp32Application.class, args);
    }

}
