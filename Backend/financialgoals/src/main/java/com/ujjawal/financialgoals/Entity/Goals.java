package com.ujjawal.financialgoals.Entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.List;

@Document("Goals")

public class Goals {

    @NotNull(message = "Goal must not be empty")
    @Id
    public String goal;


    @Min(value=5)
    public int targetAmount;


    public List<Integer> transcations;

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(int targetAmount) {
        this.targetAmount = targetAmount;
    }

    public List<Integer> getTranscations() {
        return transcations;
    }

    public void setTranscations(List<Integer> transcations) {
        this.transcations = transcations;
    }

    @Override
    public String toString() {
        return "Goals{" +
                "goal='" + goal + '\'' +
                ", targetAmount=" + targetAmount +
                ", transcations=" + transcations +
                '}';
    }
}
