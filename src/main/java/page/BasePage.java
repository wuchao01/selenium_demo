package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
    }

    public void click(By by){
        driver.findElement(by).click();
    }

    public void sendKeys(By by,String context){
        driver.findElement(by).sendKeys(context);
    }

    public void quitBrowser(){
        driver.quit();
    }
}
