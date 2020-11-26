package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CaseData {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    private WebDriver driver;

    public void run(){
        steps.forEach(step -> {
            if (step.keySet().contains("chrome")){
                driver = new ChromeDriver();
            }else if (step.keySet().contains("get")){
                driver.get(step.get("get").toString());
            } else if (step.keySet().contains("implicitlyWait")){
                driver.manage().timeouts().implicitlyWait(Integer.parseInt(step.get("implicitlyWait").toString()),TimeUnit.SECONDS);
            }else if (step.keySet().contains("find")){
                ArrayList<By> arrayList = new ArrayList();

            }
        });
    }

}
