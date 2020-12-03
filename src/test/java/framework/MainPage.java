package framework;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage{
   private SearchPage search;
   public MainPage(){
      before();
   }

   public void before(){
      driver = new ChromeDriver();
      driver.get("http://ceshiren.com");
      driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
   }

   public SearchPage Search(){
      return search = new SearchPage(driver);
   }
}
