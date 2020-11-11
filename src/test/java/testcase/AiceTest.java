package testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AiceTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        //隐式等待，毫秒为单位
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,5);
    }

    @Test
    public void login(){
        driver.get("https://ceshiren.com/");
        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
        driver.findElement(By.id("login-account-name")).clear();
        driver.findElement(By.id("login-account-name")).sendKeys("leanwu");
        driver.findElement(By.id("login-account-password")).clear();
        driver.findElement(By.id("login-account-password")).sendKeys("wuchao910420");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void waitTest(){
        driver.get("https://ceshiren.com/");

        //定制化显示等待，通常用于特殊页面等待操作，可单独封装对应等待操作
        WebElement loginEle = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//span[contains(text(),'登录')]"));
            }
            });
        loginEle.click();

//        //自带方法
//        WebElement loginEle = wait.until(presenceOfElementLocated(By.xpath("//span[contains(text(),'登录')]")));
//        loginEle.click();
    }

    public void tradDown(){
        driver.quit();
    }
}
