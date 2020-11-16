package page;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MainPage extends BasePage{
    public MainPage() throws IOException, InterruptedException {
        this.beforeAll();
    }

    public void beforeAll() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        if(file.exists()){
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://work.weixin.qq.com/wework_admin/frame");
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>(){};
            List<HashMap<String, Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
            System.out.println(cookies);
            cookies.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
            });
            driver.navigate().refresh();
        }else {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://work.weixin.qq.com/wework_admin/frame");
            sleep(10000);
            Set<Cookie> cookies = driver.manage().getCookies();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.writeValue(new File("cookies.yaml"),cookies);
            System.exit(0);
        }
    }

    public MainPage addMember(String username, String englishName, String acctid, String mobile){
        //添加成员
        click(By.cssSelector("[node-type=addmember] span:nth-child(2)"));
        sendKeys(By.id("username"),username);
        sendKeys(By.id("memberAdd_english_name"),englishName);
        sendKeys(By.id("memberAdd_acctid"),acctid);
        sendKeys(By.id("memberAdd_phone"),mobile);
//        click(By.linkText("修改"));
//        sendKeys(By.cssSelector(".ww_searchInput input:nth-child(3)"),"测试部");
//        click(By.linkText("霍格沃兹测试学院/测试部"));
//        click(By.linkText("确认"));
//        click(By.cssSelector("[name=sendInvite]"));
        click(By.linkText("保存"));
        return this;
    }

    public MainPage updateMember(String userName,String updateName, String updateEnglishName) {
//        WebElement nameEle = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title=小吴]")));
//        nameEle.click("[title=小吴]");
        click(By.cssSelector("[title=" + userName + "]"));
        click(By.linkText("编辑"));
        sendKeys(By.id("username"),updateName);
        sendKeys(By.id("memberEdit_english_name"),updateEnglishName);
        click(By.linkText("保存"));
        return this;
    }

    public MainPage deleteMember(){
        click(By.linkText("删除"));
        click(By.linkText("确认"));
        return this;
    }

    public ContactPage contact() {
        click(By.id("menu_contacts"));
        return new ContactPage(driver,wait);
    }


}
