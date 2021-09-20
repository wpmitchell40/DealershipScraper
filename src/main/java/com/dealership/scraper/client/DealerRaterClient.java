package com.dealership.scraper.client;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dealership.scraper.dto.RatingData;
import com.dealership.scraper.dto.ReviewData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DealerRaterClient {

    private String reviewsPage = "https://www.dealerrater.com/dealer/McKaig-Chevrolet-Buick-A-Dealer-For-The-People-dealer-reviews-23685/page";

    private static String reviewsCss = "div.review-entry";
    private static String dateCss = "div.italic.col-xs-6.col-sm-12.pad-none.margin-none.font-20";
    private static String ratingCss = "div.rating-static.hidden-xs";
    private static String reviewTextCss = "p.font-16.review-content.margin-bottom-none.line-height-25";
    private static String reviewTitleCss = "h3.no-format.inline.italic-bolder.font-20.dark-grey";
    private static String usernameCss = "span.italic.font-18.black.notranslate";


    public List<ReviewData> getReviews(int pagesToRead) throws IOException {
        ArrayList<ReviewData> allExtractedReviews = new ArrayList<>();
        for(int i = 1; i <= pagesToRead; i++) {
            Connection conn = Jsoup.connect(reviewsPage+i);
            Document doc = conn.get();
            Elements allReviews = doc.select(reviewsCss); //Retrieve all reviews for this current page
            ArrayList<ReviewData> extractedReviews = new ArrayList<>();
            for (Element review : allReviews){
                ReviewData reviewData = extractReviewData(review);
                extractedReviews.add(reviewData);
            }
            allExtractedReviews.addAll(extractedReviews);
        }
        return allExtractedReviews;
    }

    private ReviewData extractReviewData(Element review) throws UnexpectedException {
        String date = getDate(review);
        RatingData ratings = getRatings(review);
        String reviewTitle = getReviewTitle(review);
        String reviewText = getReviewText(review);
        String username = getUsername(review);
        //double positivityRating = nlpClient.getPositivityRating(reviewText); TODO implement NLP client
        ReviewData reviewData = new ReviewData(date, ratings, reviewTitle, reviewText, username);
        return reviewData;
    }

    private String getDate(Element reviewEntry){
        Elements dateValue = reviewEntry.select(dateCss);
        return dateValue.text();
    }

    private RatingData getRatings(Element reviewEntry) throws UnexpectedException {
        Elements ratingElements = reviewEntry.select(ratingCss);
        String ratingDiv = ratingElements.toString();
        Pattern ratingPattern = Pattern.compile("\\d{2}"); //Match the 2 digit div attribute to the star rating (e.g. 50 = 5.0 stars)
        Matcher matcher = ratingPattern.matcher(ratingDiv);
        if (matcher.find()) {
            double overallRating = Double.parseDouble(matcher.group()) / 10;
            return new RatingData(overallRating);
        } else {
            throw new UnexpectedException("Rating not found");
        }
        //TODO: parse individual ratings

    }

    private String getReviewText(Element reviewEntry){
        Elements reviewText = reviewEntry.select(reviewTextCss);
        return reviewText.text();
    }

    private String getReviewTitle(Element reviewEntry){
        Elements title = reviewEntry.select(reviewTitleCss);
        return title.text();
    }

    private String getUsername(Element reviewEntry){
        Elements username = reviewEntry.select(usernameCss);
        return username.text().substring(2); //first 2 characters are always '- ' which are not part of the username
    }
}
