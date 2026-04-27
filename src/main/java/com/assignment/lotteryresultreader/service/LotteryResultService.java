package com.assignment.lotteryresultreader.service;

import com.assignment.lotteryresultreader.dto.LotteryResultDto;
import com.assignment.lotteryresultreader.entity.LotteryResult;
import com.assignment.lotteryresultreader.repository.LotteryResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LotteryResultService {

    private final LotteryResultRepository repository;

    public LotteryResultDto collectLotteryResult() {

        String lotteryName = "Jayoda";
        String drawDetails = "Jayoda Lottery - Draw Date: " + LocalDate.now();
        String numbers = "11 33 57 70";

        return new LotteryResultDto(lotteryName, drawDetails, numbers);
    }

    public void saveLotteryResult(LotteryResultDto dto) {

        LotteryResult entity = new LotteryResult(
                dto.getLotteryName(),
                dto.getDrawDetails(),
                dto.getNumbers()
        );

        repository.save(entity);

        System.out.println("Lottery result saved successfully to database.");
    }
}