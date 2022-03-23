package webtable.tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webtable.utils.BrowserUtils;
import webtable.utils.WebDriverFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
/*
Feature:
As an Engr. Candidate
I need to automate http://www.way2automation.com/angularjs-protractor/webtables/
So I can show my awesome automation skills
 - Scenario: Add a user and validate the user has been added to the table
 - Scenario: Delete user User Name: novak and validate user has been delete
 */

public class AddDeleteUserTest {
    WebDriver driver;
    Faker faker = new Faker();
    String url = "http://www.way2automation.com/angularjs-protractor/webtables/";
    String firstNamef = faker.name().firstName();

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
    }

@Test
    public void addNewUserTest(){

    WebElement addUser = driver.findElement(By.xpath("//i[@class='icon icon-plus']"));
    addUser.click();

    WebElement firstName = driver.findElement(By.xpath("//input[@name='FirstName']"));
    firstName.sendKeys(firstNamef);

    WebElement lastName = driver.findElement(By.xpath("//input[@name='LastName']"));
    lastName.sendKeys(faker.name().lastName());

    WebElement userName = driver.findElement(By.xpath("//input[@name='UserName']"));
    userName.sendKeys(faker.name().username());

    WebElement password = driver.findElement(By.xpath("//input[@name='Password']"));
    password.sendKeys(faker.internet().password());

    WebElement companyAAA = driver.findElement(By.xpath("//input[@value='15']"));
    companyAAA.click();

    Select selectRole = new Select(driver.findElement(By.xpath("//select[@name='RoleId']")));
    selectRole.selectByValue("2");

    WebElement email = driver.findElement(By.xpath("//input[@name='Email']"));
    email.sendKeys(faker.internet().emailAddress());

    WebElement cellPhone = driver.findElement(By.xpath("//input[@name='Mobilephone']"));
    cellPhone.sendKeys(faker.phoneNumber().cellPhone());

    WebElement save = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
    save.click();

    WebElement newUser = driver.findElement(By.xpath("//table[@table-title='Smart Table example']/tbody/tr/td[1]"));
    String actualResult = newUser.getText();
    String expectedResult = firstNamef;
    Assert.assertEquals( expectedResult, actualResult);

}
@Test
public void deleteNewUserTest(){
        WebElement tableBeforeDelete = driver.findElement(By.xpath("//table[@table-title='Smart Table example']"));
        String novak = tableBeforeDelete.getText();
        Assert.assertTrue(novak.contains("Novak"));

    WebElement deleteNovak = driver.findElement(By.xpath("(//i[@class='icon icon-remove'])[3]"));
    deleteNovak.click();
    WebElement confirmDelete = driver.findElement(By.xpath("//button[@class='btn ng-scope ng-binding btn-primary']"));
    confirmDelete.click();
    BrowserUtils.sleep(1);
    WebElement tableAfterDelete = driver.findElement(By.xpath("//table[@table-title='Smart Table example']"));
    String noNovak = tableAfterDelete.getText();
    Assert.assertFalse(noNovak.contains("Novak"));

}
}
