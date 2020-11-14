package business;

import logic.AddMemberLogic;
import org.openqa.selenium.WebDriver;

public class AddMember {
    public WebDriver driver;

    public void addMember(){
        AddMemberLogic addMemberLogic = new AddMemberLogic(driver);
        addMemberLogic.addMember("小吴","吃土阿加西","2020111301","18516121801");
    }
}
