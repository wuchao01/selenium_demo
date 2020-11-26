package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CaseData {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    private WebDriver driver;
    private WebElement element;

    public void run(){
        steps.forEach(step -> {
            if (step.keySet().contains("chrome")){
                driver = new ChromeDriver();
            }else if (step.keySet().contains("get")){
                driver.get(step.get("get").toString());
            } else if (step.keySet().contains("implicitlyWait")){
                System.out.println("延迟等待时间：" + step.get("implicitlyWait").toString());
                driver.manage().timeouts().implicitlyWait(Integer.parseInt(step.get("implicitlyWait").toString()),TimeUnit.SECONDS);
            }else if (step.keySet().contains("find")){
                ArrayList<By> arrayList = new ArrayList<>();
                ((HashMap<String,String>)step.get("find")).entrySet().forEach(str ->{
                    if (str.getKey().contains("id")){
                        System.out.println("元素:" + str.getValue());
                        arrayList.add(By.id(str.getValue()));
                    }
                    if (str.getKey().contains("xpath")){
                        arrayList.add(By.xpath(str.getValue()));
                    }
                    if (str.getKey().contains("cssSelector")){
                        arrayList.add(By.cssSelector(str.getValue()));
                    }
                    element = driver.findElement(arrayList.get(0));
                });
            }

            if (step.keySet().contains("click")){
                element.click();
            }
            if (step.keySet().contains("sendkeys")){
                element.sendKeys("search demo");
            }
        });
    }

}
