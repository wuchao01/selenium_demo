package testcase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WeworkDemo {
    public static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        driver = new ChromeDriver();
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome_baidu");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void getCookie() throws IOException, InterruptedException {
        Thread.sleep(10000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);
    }

    @Test
    public void login() throws IOException {
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome_baidu");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>(){};
        List<HashMap<String, Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
        System.out.println(cookies);
        cookies.forEach(cookieMap -> {
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
        });
        driver.navigate().refresh();
    }

}
