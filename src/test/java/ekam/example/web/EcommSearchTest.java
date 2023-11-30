package ekam.example.web;

import com.testvagrant.ekam.testBases.testng.WebTest;

import static com.testvagrant.ekam.commons.LayoutInitiator.*;

import ekam.example.web.pages.FlipkartHomePage;
import org.testng.annotations.Test;
import io.qameta.allure.TmsLink;

import static org.testng.Assert.*;

public class EcommSearchTest extends WebTest {

    @TmsLink("Test case id")
    @Test(groups = "web", description = "Verify if we are able to search products on a ecommerce website")
    public void webTest() {

        // 1. Arrange
        String searchText = "T-Shirts";
        String expectedTitle = "T Shirts- Buy Products Online at Best Price in India - All Categories | Flipkart.com";

        // 2. Act
        String actualTitle = Page(FlipkartHomePage.class)
                .search(searchText)
                .getTitle();

        // 3. Assert
        assertEquals(actualTitle, expectedTitle);

    }
}