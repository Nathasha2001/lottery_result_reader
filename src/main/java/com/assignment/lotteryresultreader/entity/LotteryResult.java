package com.assignment.lotteryresultreader.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lottery_results_final")
@Data
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



}