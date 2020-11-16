package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage extends BasePage{

    public ContactPage(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    public ContactPage addDepartment(String departmentName){
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),departmentName);
        click(By.linkText("选择所属部门"));
        driver.findElements(By.xpath("//a[text()='霍格沃兹测试学院']")).get(1).click();
        //下面这个方法不清楚为啥找不到，还要在试试
//        click(By.linkText("//a[text()='测试部'][1]"));
        click(By.linkText("确定"));
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    public ContactPage searchDepartment(String departmentName) {
        sendKeys(By.id("memberSearchInput"),departmentName);
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    public ContactPage updateDepartment(String departmentName){
        click(By.linkText("修改名称"));
        sendKeys(By.name("name"),departmentName);
        click(By.linkText("保存"));
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    public ContactPage deleteDepartment(){
        click(By.cssSelector(".jstree-clicked > span:last-child"));
        click(By.linkText("删除"));
        click(By.linkText("确定"));
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    //仅删除新增部门的父级部门，如需逐个删除部门需新增方法，本方法仅做数据清理
    public ContactPage clearDepartment(){
        click(By.linkText("测试部"));
//        if (driver.findElement(By.linkText("测试部")).getSize())
        if(getPartyInfo().contains("当前部门无任何成员")){
            click(By.cssSelector(".jstree-clicked > span:last-child"));
            click(By.linkText("删除"));
            click(By.linkText("确定"));
        }else {
            while(!getPartyInfo().contains("当前部门无任何成员")){
                click(By.cssSelector(".jstree-clicked > span:last-child"));
                click(By.cssSelector(".js_title input:first-child"));
                click(By.linkText("删除"));
                click(By.linkText("确认"));
            }
            click(By.cssSelector(".jstree-clicked > span:last-child"));
            click(By.linkText("删除"));
            click(By.linkText("确定"));
        }

        return this;
    }

    public ContactPage clearSearch(){
        click(By.id("clearMemberSearchInput"));
        return this;
    }

    public String getPartyInfo(){
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        return content;
    }

}
