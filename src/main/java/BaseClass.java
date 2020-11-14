import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * 放置公共控件操作方法及初始化操作
 */
public class BaseClass {
    public static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        driver = new ChromeDriver();
        //默认执行隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void treadDown(){
        driver.quit();
    }

    //刷新页面
    public void refresh(){
        driver.navigate().refresh();
    }
}
