package ekam.example.web.pages;

import com.testvagrant.ekam.reports.annotations.WebStep;
import com.testvagrant.ekam.atoms.web.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlipkartHomePage extends WebPage {

    private By searchBox = By.xpath("//input[contains(@class,'Pke_EE')]");
    private By searchButton = By.xpath("//button[contains(@class,'_2iLD__')]");

    private By loginPopUpCloseButton = By.xpath("//span[contains(@class, '_30XB9F')]");


    @WebStep(keyword = "When", description = "I hit search button")
    public FlipkartHomePage search(String text) {
        element(loginPopUpCloseButton).click();
        textbox(searchBox).setText(text);
        element(searchButton).click();
        return this;
    }

    @WebStep(keyword = "And", description = "I return title")
    public String getTitle() {
        return driver.getTitle();
    }

}