package com.dealership.scraper.service;

import com.dealership.scraper.dto.ReviewData;
import com.dealership.scraper.utils.ReviewScoreSorter;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public class ReviewRatingService {

    public ReviewRatingService(){}

    public List<ReviewData> filter(List<ReviewData> allReviews, String positiveCriteria) throws ExecutionControl.NotImplementedException {
        if (positiveCriteria.equalsIgnoreCase("nlp")){
            //this will never get called as it is not an allowed argument from the command line,
            //but it is stubbed out for future use and ease of implementation when NLP is added
            throw new ExecutionControl.NotImplementedException("Not yet implemented, nlp review comparisons will be added at a later date");
        }
        allReviews.sort(new ReviewScoreSorter(positiveCriteria));
        return allReviews.subList(0,3);
    }
}
