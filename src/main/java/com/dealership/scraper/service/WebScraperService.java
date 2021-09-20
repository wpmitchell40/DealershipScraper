package com.dealership.scraper.service;

import com.dealership.scraper.dto.ReviewData;
import com.dealership.scraper.client.DealerRaterClient;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;


public class WebScraperService {

    private DealerRaterClient dealerRaterClient;
    private ReviewRatingService reviewRatingService;

    @Autowired
    public WebScraperService(DealerRaterClient dealerRaterClient, ReviewRatingService reviewRatingService){
        this.dealerRaterClient = dealerRaterClient;
        this.reviewRatingService = reviewRatingService;

    }

    public List<ReviewData> getPositiveReviews(int pagesToRead, String positiveCriteria) throws ExecutionControl.NotImplementedException, IOException {
        List<ReviewData> allReviews = dealerRaterClient.getReviews(pagesToRead);
        List<ReviewData> mostPositiveReviews = reviewRatingService.filter(allReviews, positiveCriteria);
        return mostPositiveReviews;
    }
}
