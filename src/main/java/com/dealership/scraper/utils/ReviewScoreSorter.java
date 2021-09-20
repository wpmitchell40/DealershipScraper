package com.dealership.scraper.utils;

import com.dealership.scraper.dto.ReviewData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;

public class ReviewScoreSorter implements Comparator<ReviewData> {

    private String positiveCriteria;

    public ReviewScoreSorter(String positiveCriteria){
        this.positiveCriteria = positiveCriteria;
    }

    @Override
    public int compare(ReviewData r1, ReviewData r2){
        int comparedValue = Double.compare(r2.getRating().getOverallRating(), r1.getRating().getOverallRating());
        if (comparedValue == 0) {
            if (positiveCriteria.equalsIgnoreCase("datePosted")){
                //If the compared ratings are equal, we should instead sort based on when the review was posted
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
                LocalDate date1 = LocalDate.parse(r1.getDate(), formatter);
                LocalDate date2 = LocalDate.parse(r2.getDate(), formatter);
                return date2.compareTo(date1);
            } else if (positiveCriteria.equalsIgnoreCase("username")) {
                //If the compared ratings are equal, we should instead sort based on the username of the reviewer.
                // Everyone knows suspicious people make usernames that start with letters at the end of the English alphabet
                return r2.getUsername().compareTo(r1.getUsername());
            } else {
                //If no method of positive comparison is selected after overall star rating, the longest written review
                //is to be considered the most suspicious
                return Integer.compare(r2.getReviewText().length(), r1.getReviewText().length());
            }
        } else {
            return comparedValue;
        }
    }

    public void setPositiveCriteria(String positiveCriteria) {
        this.positiveCriteria = positiveCriteria;
    }
}
