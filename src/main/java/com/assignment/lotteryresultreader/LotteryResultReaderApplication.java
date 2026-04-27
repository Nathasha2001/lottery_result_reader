package com.assignment.lotteryresultreader;

import com.assignment.lotteryresultreader.dto.LotteryResultDto;
import com.assignment.lotteryresultreader.service.LotteryResultService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LotteryResultReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryResultReaderApplication.class, args);
    }

    @Bean
    CommandLineRunner run(LotteryResultService service) {
        return args -> {
            LotteryResultDto dto = service.collectLotteryResult();

            System.out.println("===== Lottery Result =====");
            System.out.println("Lottery Name : " + dto.getLotteryName());
            System.out.println("Draw Details : " + dto.getDrawDetails());
            System.out.println("Numbers      : " + dto.getNumbers());
            System.out.println("==========================");

            service.saveLotteryResult(dto);
        };
    }
}