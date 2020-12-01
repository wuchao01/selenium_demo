package framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeworkTest {

    @ParameterizedTest
    @MethodSource("data")
    public void search(TestCase data){
//        ChromeDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://ceshiren.com");
//        driver.findElement(By.id("search-button")).click();
//        driver.findElement(By.id("search-term")).sendKeys("search demo");
        System.out.println(data);
        data.run();
    }

    public static List<TestCase> data() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestCase data = mapper.readValue(WeworkTest.class.getResourceAsStream("/framework/data.yaml"), TestCase.class);
        return data.testCaseGenerate();
    }
}
