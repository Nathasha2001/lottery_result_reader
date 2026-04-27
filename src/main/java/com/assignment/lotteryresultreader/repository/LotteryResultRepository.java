package com.assignment.lotteryresultreader.repository;

import com.assignment.lotteryresultreader.entity.LotteryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryResultRepository extends JpaRepository<LotteryResult, Long> {
}