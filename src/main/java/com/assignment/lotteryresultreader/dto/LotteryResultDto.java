package com.assignment.lotteryresultreader.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LotteryResultDto {

    private String lotteryName;
    private String drawDetails;
    private String numbers;

    public LotteryResultDto(String lotteryName, String drawDetails, String numbers) {
        this.lotteryName = lotteryName;
        this.drawDetails = drawDetails;
        this.numbers = numbers;
    }
}