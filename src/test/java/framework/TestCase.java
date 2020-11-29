package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    private WebDriver driver;
    private WebElement element;
    public int index = 0;

    public Object getValue(HashMap<String,Object> step, String key){
        Object value = step.get(key);
        //解析yaml得到参数，复杂结构应用递归实现判断
        if (value instanceof String){
            return ((String) value).replace("$data",data.get(index));
        }else {
            return value;
        }
    }

    public ArrayList<TestCase> testCaseGenerate(){
        ArrayList<TestCase> testCaseList = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            TestCase testCaseNew = new TestCase();
            testCaseNew.index = i;
            testCaseNew.steps = steps;
            testCaseNew.data = data;
            testCaseList.add(testCaseNew);
        }
        return testCaseList;
    }

    //testCase引擎，复杂解析yaml生成关键字驱动用例
    public void run(){
        steps.forEach(step -> {
            if (step.keySet().contains("chrome")){
                driver = new ChromeDriver();
            }else if (step.keySet().contains("get")){
                driver.get(getValue(step,"get").toString());
            } else if (step.keySet().contains("implicitlyWait")){
                System.out.println("延迟等待时间：" + getValue(step,"implicitlyWait").toString());
                driver.manage().timeouts().implicitlyWait(
                        Long.valueOf(getValue(step,"implicitlyWait").toString()),TimeUnit.SECONDS);
            }else if (step.keySet().contains("find")){
                ArrayList<By> arrayList = new ArrayList<>();
                ((HashMap<String,String>)getValue(step,"find")).entrySet().forEach(str ->{
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
                element.sendKeys(getValue(step,"sendkeys").toString());
            }
        });
    }

}
