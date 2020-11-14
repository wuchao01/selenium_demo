package page;

import org.openqa.selenium.By;

public class AddMemberPage {
    public static By addMember = new By.ByCssSelector("[node-type=addmember] span:nth-child(2)");
    public static By username = new By.ById("username");
    public static By englishName = new By.ById("memberAdd_english_name");
    public static By account = new By.ById("memberAdd_acctid");
    public static By phone = new By.ById("memberAdd_phone");
    public static By sendInvite = new By.ByCssSelector("[name=sendInvite]");
    public static By save = new By.ByCssSelector("a.js_btn_save");

}
