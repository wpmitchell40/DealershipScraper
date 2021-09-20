package com.dealership.scraper.unit.service;

import com.dealership.scraper.dto.RatingData;
import com.dealership.scraper.dto.ReviewData;
import com.dealership.scraper.helper.UnitTestHelper;
import com.dealership.scraper.service.ReviewRatingService;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviewRatingServiceUnitTest {

    ReviewRatingService reviewRatingService = new ReviewRatingService();

    private UnitTestHelper unitTestHelper = new UnitTestHelper();

    @Test
    public void testFilterReducesSizeTo3() throws ExecutionControl.NotImplementedException {
        ArrayList<ReviewData> allReviews = new ArrayList<>();
        for(int i = 0; i <= 10; i++){
            allReviews.add(unitTestHelper.createReviewData());
        }
        assertEquals(3, reviewRatingService.filter(allReviews, "n/a").size());
    }

    @Test
    public void testFilterFindsBestReviews() throws ExecutionControl.NotImplementedException {
        ArrayList<ReviewData> allReviews = new ArrayList<>();
        for(int i = 0; i <= 10; i++){
            if (i <= 8){
                allReviews.add(unitTestHelper.createReviewData());
            } else {
                ReviewData data = unitTestHelper.createReviewData();
                data.setRating(new RatingData(5.0));
                allReviews.add(data);
            }
        }
        List<ReviewData> filteredReviews = reviewRatingService.filter(allReviews, "n/a");
        assertEquals(3, filteredReviews.size());
        assertEquals(5.0, filteredReviews.get(0).getRating().getOverallRating());
        assertEquals(5.0, filteredReviews.get(1).getRating().getOverallRating());
        assertEquals(4.7, filteredReviews.get(2).getRating().getOverallRating());
    }
}
