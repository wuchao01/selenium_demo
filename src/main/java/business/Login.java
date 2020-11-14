package business;

import logic.LoginLogic;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Login {
    public WebDriver driver;

    //登录企业微信
    public void login() throws IOException, InterruptedException {
        LoginLogic loginLogic = new LoginLogic(driver);
        loginLogic.getCookie();
        loginLogic.login();
    }
}
