package com.assignment.lotteryresultreader.service;

import com.assignment.lotteryresultreader.dto.LotteryResultDto;
import com.assignment.lotteryresultreader.entity.LotteryResult;
import com.assignment.lotteryresultreader.repository.LotteryResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotteryResultService {

    private final LotteryResultRepository repository;

    public LotteryResultDto collectLotteryResult() {

        try {
            String url = "https://www.dlb.lk/result/en";

            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/120.0 Safari/537.36")
                    .header("Accept", "text/html")
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .ignoreHttpErrors(true)
                    .timeout(15000)
                    .get();

            String pageText = document.body().text();

            String lotteryName = "Jayoda";
            String drawDetails = "Jayoda Lottery - Draw Date: " + LocalDate.now();
            String numbers = extractNumbers(pageText);

            return new LotteryResultDto(lotteryName, drawDetails, numbers);

        } catch (Exception e) {
            log.error("Error while collecting lottery result.");
            log.error("Reason: " + e.getMessage());

            return new LotteryResultDto(
                    "Jayoda",
                    "Failed to collect from website",
                    "No result collected"
            );
        }
    }

    private String extractNumbers(String pageText) {

        int index = pageText.toLowerCase().indexOf("jayoda");

        if (index == -1) {
            return "Jayoda result not found";
        }

        String section = pageText.substring(index);

        String[] words = section.split("\\s+");

        StringBuilder result = new StringBuilder();

        for (String word : words) {

            String cleanedWord = word.replaceAll("[^0-9]", "");

            if (cleanedWord.matches("\\d{1,2}")) {
                result.append(cleanedWord).append(" ");
            }

            String currentResult = result.toString().trim();

            if (!currentResult.isEmpty() && currentResult.split("\\s+").length == 4) {
                break;
            }
        }

        if (result.toString().trim().isEmpty()) {
            return "No numbers found";
        }

        return result.toString().trim();
    }

    public void saveLotteryResult(LotteryResultDto dto) {

        LotteryResult entity = new LotteryResult();

        entity.setLotteryName(dto.getLotteryName());
        entity.setNumbers(dto.getNumbers());
        entity.setDrawDetails(dto.getDrawDetails());






        repository.save(entity);

        log.info("Lottery result saved successfully to database.");
    }
}