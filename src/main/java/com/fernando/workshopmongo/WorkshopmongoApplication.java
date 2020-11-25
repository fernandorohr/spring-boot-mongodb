package com.fernando.workshopmongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WorkshopmongoApplication {

    public static void main(String... args) {
        log.info("[WorkshopmongoApplication] ::: has started!!! ::: [WorkshopmongoApplication]");
        SpringApplication.run(WorkshopmongoApplication.class, args);
    }

}
