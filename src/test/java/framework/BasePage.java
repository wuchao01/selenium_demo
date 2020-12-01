package framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class BasePage {
    static BasePage instance = null;
    HashMap<String,BasePage> pages = new HashMap<>();
    HashMap<String, List<HashMap<String, Object>>> yamlSource=new HashMap<>();
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static synchronized BasePage getInstance() {
        if(instance == null){
            instance = new BasePage();
        }
        return instance;
    }

    public BasePage() {
    }

    public void poInit(String name,String className) throws Exception {
        BasePage pageClass = (BasePage) Class.forName(className).getDeclaredConstructor().newInstance();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String,List<HashMap<String,Object>>>> typeReference = new TypeReference<HashMap<String, List<HashMap<String, Object>>>>() {
        };
        pageClass.yamlSource = mapper.readValue(ParamsTest.class.getResourceAsStream(String.format("/framework/%s",className)),typeReference);
//        pageClass.getPO("main_po.yaml").stepRun("search");
        pages.put(name,pageClass);
        pageClass.stepRun("init");
    }

    BasePage getPO(String name){
        return pages.get(name);
    }

    public void stepRun(String method){
        Method methedJava = Arrays.stream(this.getClass().getDeclaredMethods()).filter(m -> m.getName().equals(method)).findFirst().get();
        try {
            methedJava.invoke(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
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
