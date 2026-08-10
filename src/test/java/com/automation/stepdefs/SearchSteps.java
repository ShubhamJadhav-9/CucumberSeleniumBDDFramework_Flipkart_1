package com.automation.stepdefs;

import com.automation.pages.FlipkartHomePage;
import com.automation.pages.SearchResultsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {
    FlipkartHomePage homePage = new FlipkartHomePage();
    SearchResultsPage resultsPage = new SearchResultsPage();

    @Given("user is on Flipkart homepage")
    public void user_is_on_flipkart_homepage() {
        homePage.openFlipkart();
    }

    @When("user searches for {string}")
    public void user_searches_for(String product) {
        homePage.searchFor(product);
    }

    @Then("search results should be displayed for {string}")
    public void search_results_should_be_displayed_for(String product) {
        assert resultsPage.isResultsDisplayedFor(product) : "Results not found for " + product;
    }
}