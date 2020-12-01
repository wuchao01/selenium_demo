package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage{
    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchPage search(String text){
       click(By.id("search-button"));
       sendKeys(By.id("search-term"),text);
       return this;
   }
}
