package com.assignment.lotteryresultreader.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lottery_results_final")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LotteryResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lottery_name")
    private String lotteryName;

    @Column(name = "draw_details")
    private String drawDetails;

    @Column(name = "numbers", columnDefinition = "TEXT")
    private String numbers;

    public LotteryResult(String lotteryName, String drawDetails, String numbers) {
        this.lotteryName = lotteryName;
        this.drawDetails = drawDetails;
        this.numbers = numbers;
    }
}