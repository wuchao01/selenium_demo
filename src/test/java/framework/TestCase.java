package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCase {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    public WebDriver driver;
    public WebElement element;
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

    public List<TestCase> testCaseGenerate(){
        List<TestCase> testCaseList = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            SeleniumTest testCaseNew = new SeleniumTest();
            testCaseNew.index = i;
            testCaseNew.steps = steps;
            testCaseNew.data = data;
            testCaseList.add(testCaseNew);
        }
        return testCaseList;
    }

    //testCase引擎，复杂解析yaml生成关键字驱动用例
    public void run(){

    }

}
