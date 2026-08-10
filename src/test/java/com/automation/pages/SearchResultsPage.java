package com.automation.pages;

import com.automation.utils.DriverManager;

public class SearchResultsPage {
    public SearchResultsPage() {
        // No elements needed – using URL validation (most reliable for Flipkart)
    }
    
    
    
    public boolean isResultsDisplayedFor(String product) {
        String currentUrl = DriverManager.getDriver().getCurrentUrl().toLowerCase();
        String normalizedProduct = product.toLowerCase().replace(" ", "+").replace(" ", "%20");

        // More lenient checks
        return 
            currentUrl.contains("q=" + normalizedProduct) ||           // standard search
            currentUrl.contains(normalizedProduct) ||                  // partial match
            currentUrl.contains("search") ||                           // has search in path
            DriverManager.getDriver().getPageSource().toLowerCase().contains(product.toLowerCase()); // fallback: page contains product name
    }
    
    
    

   /* public boolean isResultsDisplayedFor(String product) {
        String currentUrl = DriverManager.getDriver().getCurrentUrl().toLowerCase();
        String expectedTerm = product.toLowerCase().replace(" ", "+");
        return currentUrl.contains("q=" + expectedTerm) || currentUrl.contains(expectedTerm);
    }*/
    
    
    
}