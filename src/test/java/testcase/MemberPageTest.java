package testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import page.MainPage;

import java.io.IOException;
import java.util.stream.Stream;

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
        main.addMember(username,englishName,acctid,mobile).updateMember("小吴","小天","阿加西")
        .deleteMember();
    }

//    单独写始终无法执行，暂时没找到原因
//    @Order(2)
//    @ParameterizedTest
//    @MethodSource("updateUserInfo")
//    public void testUpdateDepartment(String userName,String updateName,String updateEnglishName){
//        main.updateMember(userName,updateName,updateEnglishName).deleteMember();
//    }

    public static Stream<Arguments> getUserInfo(){
        return Stream.of(Arguments.of("小吴","吃土阿加西","20201115","13711122234"));
    }

//    public static Stream<Object> updateUserInfo(){
//        return Stream.of(Arguments.of("小吴"),Arguments.of("小天","阿加西"));
//    }
}
