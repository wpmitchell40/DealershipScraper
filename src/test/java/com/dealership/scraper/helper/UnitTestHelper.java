package com.dealership.scraper.helper;

import com.dealership.scraper.dto.RatingData;
import com.dealership.scraper.dto.ReviewData;

public class UnitTestHelper {

    public ReviewData createReviewData(){
        RatingData ratingData = new RatingData(4.7);
        return new ReviewData("July 26, 2020", ratingData, "What a good place",
                "Lorem Ipsum dolor sit amet", "user");
    }
}
