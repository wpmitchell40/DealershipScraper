package com.dealership.scraper.unit.utils;

import com.dealership.scraper.dto.RatingData;
import com.dealership.scraper.dto.ReviewData;
import com.dealership.scraper.helper.UnitTestHelper;
import com.dealership.scraper.utils.ReviewScoreSorter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ReviewScoreSorterUnitTest {

    UnitTestHelper unitTestHelper = new UnitTestHelper();

    @InjectMocks
    private ReviewScoreSorter reviewScoreSorter;

    @Test
    public void testDefaultScoreRatingSortsCorrectly(){
        ReviewData review1 = unitTestHelper.createReviewData(); //creates a dummy reviewData with a score of 4.7/5
        ReviewData review2 = unitTestHelper.createReviewData();

        review2.setRating(new RatingData(5.0));
        ArrayList<ReviewData> toSort = new ArrayList<>();
        toSort.add(review1);
        toSort.add(review2);
        assertTrue(reviewScoreSorter.compare(review1, review2) > 0);

    }

    @Test
    public void testTiebreakerLengthSortsCorrectly(){
        reviewScoreSorter.setPositiveCriteria("");
        ReviewData review1 = unitTestHelper.createReviewData(); //creates a dummy reviewData with a score of 4.7/5
        ReviewData review2 = unitTestHelper.createReviewData();

        review2.setReviewText("A MUCH MUCH LONGER REVIEW THAN THE ONE THAT SAY LOREM IPSUM DOLOR SIT AMET");
        ArrayList<ReviewData> toSort = new ArrayList<>();
        toSort.add(review1);
        toSort.add(review2);
        assertTrue(reviewScoreSorter.compare(review1, review2) > 0);
    }

    @Test
    public void testTiebreakerUsernameSortsCorrectly(){
        reviewScoreSorter.setPositiveCriteria("username");
        ReviewData review1 = unitTestHelper.createReviewData(); //creates a dummy reviewData with a score of 4.7/5
        ReviewData review2 = unitTestHelper.createReviewData();

        review2.setUsername("zelda");
        ArrayList<ReviewData> toSort = new ArrayList<>();
        toSort.add(review1);
        toSort.add(review2);
        assertTrue(reviewScoreSorter.compare(review1, review2) > 0);
    }

    @Test
    public void testTiebreakerDatePostedSortsCorrectly(){
        reviewScoreSorter.setPositiveCriteria("datePosted");
        ReviewData review1 = unitTestHelper.createReviewData(); //creates a dummy reviewData with a score of 4.7/5
        ReviewData review2 = unitTestHelper.createReviewData();

        review2.setDate("January 1, 1970");
        ArrayList<ReviewData> toSort = new ArrayList<>();
        toSort.add(review1);
        toSort.add(review2);
        assertTrue(reviewScoreSorter.compare(review1, review2) < 0);
    }

}
