package testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import page.MainPage;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MemberPageTest {

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
    @MethodSource("getUserInfo")
    public void testMember(String username,String englishName,String acctid,String mobile) {
        main.addMember(username,englishName,acctid,mobile);
    }

    @ParameterizedTest
    @MethodSource("updateUserInfo")
    public void testUpdateDepartment(String username,String updateName,String updateEnglishName){
        main.updateMember(username,updateName,updateEnglishName).deleteMember(updateName);
    }

    public static Stream<String> getUserInfo(){
        return Stream.of("小吴","吃土阿加西","20201115","137111222345");
    }

    public static Stream<Arguments> updateUserInfo(){
        return Stream.of(arguments("小吴"),arguments("小天","阿加西"));
    }
}
