package logic;

import org.openqa.selenium.WebDriver;
import page.AddMemberPage;

public class AddMemberLogic {
    public WebDriver driver;

    public AddMemberLogic(WebDriver driver){
        this.driver = driver;
    }

    //添加成员
    public void addMember(String username,String englishName,String accountId,String phone){
        driver.findElement(AddMemberPage.account).click();
        driver.findElement(AddMemberPage.username).sendKeys(username);
        driver.findElement(AddMemberPage.englishName).sendKeys(englishName);
        driver.findElement(AddMemberPage.account).sendKeys(accountId);
        driver.findElement(AddMemberPage.phone).sendKeys(phone);
        driver.findElement(AddMemberPage.sendInvite).click();
        driver.findElement(AddMemberPage.save).click();
    }
}
