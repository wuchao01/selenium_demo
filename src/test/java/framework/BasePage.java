package framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BasePage {
    static BasePage instance = null;
    HashMap<String,BasePage> pages = new HashMap<>();
    HashMap<String, List<HashMap<String, Object>>> yamlSource=new HashMap<>();
    private SeleniumTest seleniumTest = new SeleniumTest();
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
        //动态创建类并实例化为一个对象
//        BasePage pageClass = (BasePage) Class.forName(className).getDeclaredConstructor().newInstance();
        BasePage pageClass = new BasePage();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
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
//        反射找java方法
//        Method methodJava = Arrays.stream(this.getClass().getDeclaredMethods()).filter(m -> m.getName().equals(method)).findFirst().get();
//        try {
//            methodJava.invoke(this);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        List<HashMap<String, Object>> steps = yamlSource.get(method);
        seleniumTest.steps = steps;
        seleniumTest.data = Arrays.asList("");
        seleniumTest.run();
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
