package com.dealership.scraper.dto;

public class ReviewData {
    String date;
    RatingData rating;
    String title;
    String username;
    String reviewText;

    public ReviewData(String date, RatingData ratings, String reviewTitle, String reviewText, String username) {
        this.date = date;
        this.rating = ratings;
        this.title = reviewTitle;
        this.reviewText = reviewText;
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public RatingData getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRating(RatingData rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
