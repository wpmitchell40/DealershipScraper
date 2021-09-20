package com.dealership.scraper.dto;

public class RatingData {
    private double customerServiceRating;
    private double qualityOfWork;
    private double friendlinessRating;
    private double pricingRating;
    private double overallRating;
    private boolean recommendedDealer;

    public RatingData(){
        /*
        other ratings are available to peruse at the scraper level,
        but implementing all this would take additional time
         */
    }

    public RatingData(double overallRating){
        this.overallRating = overallRating;
    }

    public double getCustomerServiceRating() {
        return customerServiceRating;
    }

    public void setCustomerServiceRating(double customerServiceRating) {
        this.customerServiceRating = customerServiceRating;
    }

    public double getQualityOfWork() {
        return qualityOfWork;
    }

    public void setQualityOfWork(double qualityOfWork) {
        this.qualityOfWork = qualityOfWork;
    }

    public double getFriendlinessRating() {
        return friendlinessRating;
    }

    public void setFriendlinessRating(double friendlinessRating) {
        this.friendlinessRating = friendlinessRating;
    }

    public double getPricingRating() {
        return pricingRating;
    }

    public void setPricingRating(double pricingRating) {
        this.pricingRating = pricingRating;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public boolean isRecommendedDealer() {
        return recommendedDealer;
    }

    public void setRecommendedDealer(boolean recommendedDealer) {
        this.recommendedDealer = recommendedDealer;
    }

}
