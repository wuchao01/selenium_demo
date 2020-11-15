package testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import page.MainPage;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ContactPageTest {

    private static MainPage main;

    @BeforeAll
    public static void beforeAll() throws IOException, InterruptedException {
        main = new MainPage();
//        main.contact().clearDepartment();
    }

//    @AfterAll
//    public static void afterAll() {
//        main.quitBrowser();
//    }

    @ParameterizedTest
    @MethodSource("getDepartmentName")
    public void testAddDepartment(String departmentName) {
        assertTrue(main.contact().addDepartment(departmentName).getPartyInfo().contains("当前部门无任何成员"));
    }

    @ParameterizedTest
    @MethodSource("getUpdateDepartmentName")
    public void testUpdateDepartment(String departmentName,String updateDepartmentName){
        main.contact().searchDepartment(departmentName)
                .updateDepartment(updateDepartmentName).clearSearch();
    }

    public static Stream<String> getDepartmentName(){
        return Stream.of("测试一部");
    }

    public static Stream<Arguments> getUpdateDepartmentName(){
        return Stream.of(arguments("测试一部","质量管理一部"));
    }
}
