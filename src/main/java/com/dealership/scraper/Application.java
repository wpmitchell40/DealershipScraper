package com.dealership.scraper;

import com.dealership.scraper.client.DealerRaterClient;
import com.dealership.scraper.dto.ReviewData;
import com.dealership.scraper.service.ReviewConsoleOutputService;
import com.dealership.scraper.service.ReviewRatingService;
import com.dealership.scraper.service.WebScraperService;
import jdk.jshell.spi.ExecutionControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class Application {

	private static Logger LOG = LoggerFactory
			.getLogger(Application.class);

	public static void main(String[] args) throws ExecutionControl.NotImplementedException, IOException {
		String positiveCriteria;
		ArrayList<String> allowedArgs = new ArrayList<String>(Arrays.asList("username", "datePosted", "reviewLength"));
		if(args.length != 1)
		{
			positiveCriteria = "reviewLength";
		} else {
			positiveCriteria = args[0];
			if(!allowedArgs.contains(positiveCriteria)){
				positiveCriteria = "reviewLength";
			}
		}
		LOG.info("Starting Dealership Scraper with tiebreaker mode - " + positiveCriteria);
		WebScraperService webScraperService = new WebScraperService(new DealerRaterClient(), new ReviewRatingService());
		ReviewConsoleOutputService outputService = new ReviewConsoleOutputService();
		List<ReviewData> mostPositiveReviews = webScraperService.getPositiveReviews(5, positiveCriteria); //added ability for making the scraper pull more than 5 pages in the future
		outputService.printToConsole(mostPositiveReviews);
		LOG.info("Dealership Scraper Task Complete");
	}

}
