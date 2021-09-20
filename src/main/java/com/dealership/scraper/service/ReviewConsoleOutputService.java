package com.dealership.scraper.service;

import com.dealership.scraper.dto.ReviewData;

import java.util.List;

public class ReviewConsoleOutputService {

    public void printToConsole(List<ReviewData> reviewsToPost){
        System.out.println("--------------------------------------------------");
        for(ReviewData review : reviewsToPost){
            System.out.println("SUSPICIOUS REVIEW:");
            System.out.println("Title - " + review.getTitle());
            System.out.println("By User - " + review.getUsername());
            System.out.println("Posted on - " + review.getDate());
            System.out.println("With a rating of - " + review.getRating().getOverallRating() + " out of 5.0");
            System.out.println("Review Contents - " + review.getReviewText());
            System.out.println("--------------------------------------------------");
        }
    }
}
