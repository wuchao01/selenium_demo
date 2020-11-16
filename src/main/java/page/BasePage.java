package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public BasePage() {
    }

    public void click(By by){
        driver.findElement(by).click();
    }

    public void sendKeys(By by,String context){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(context);
    }

    public void quitBrowser(){
        driver.quit();
    }
}
