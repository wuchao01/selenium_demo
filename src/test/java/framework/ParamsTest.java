package framework;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

public class ParamsTest{

    @ParameterizedTest
    @MethodSource("search")
    public void data(TestCase testCase){
        testCase.run();
    }

    public static List<TestCase> search() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestCase testCase = mapper.readValue(ParamsTest.class.getResourceAsStream("/framework/search_po_test.yaml"),POTestCase.class);
        return testCase.testCaseGenerate();
    }

}
