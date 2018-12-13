
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class OrangeTest {


    WebDriver driver;

    String employeeName  = "Russel Hamilton";
    String username = "ahmedaliq";
    String passowrd = "aliabouaaa";
    String xpath = "//a[text()='ahmedaliq']";

    @BeforeMethod
    public void Setup(){

        System.setProperty("webdriver.chrome.driver", "/Users/ahmedabougarboouh/Desktop/chromedriver ");
        driver = new ChromeDriver();
      //  driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       //driver.manage().window().maximize();
        //driver.manage().deleteAllCookies();
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        //login("admin", "admin123");


    }

    @Test(priority = 1)

        public void login(){
        driver.findElement(By.xpath("//input[@name='txtUsername'][@id='txtUsername']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='txtPassword'][@id='txtPassword']")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@value='LOGIN'][@id='btnLogin']")).click();
        String valid = driver.findElement(By.xpath("//a[@id='welcome']")).getText();
        Assert.assertEquals(valid,"Welcome Admin");

        /*
        or  Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"welcome\"]")).isDisplayed());

         */
    }

    @Test(priority = 2)

        public void invaild_password_login(){
        driver.findElement(By.xpath("//input[@name='txtUsername'][@id='txtUsername']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='txtPassword'][@id='txtPassword']")).sendKeys("admin13");
        driver.findElement(By.xpath("//input[@value='LOGIN'][@id='btnLogin']")).click();
        String valid = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
        Assert.assertEquals(valid,"Invalid credentials");

    }

    @Test(priority = 3)

    public void invalidUserName() {
        driver.findElement(By.xpath("//input[@name='txtUsername'][@id='txtUsername']")).sendKeys("admin1");
        driver.findElement(By.xpath("//input[@name='txtPassword'][@id='txtPassword']")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@value='LOGIN'][@id='btnLogin']")).click();
        String valid = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
        Assert.assertEquals(valid,"Invalid credentials");

    }

    @Test(priority = 4)
    public void emptyUsername(){
        driver.findElement(By.xpath("//input[@name='txtUsername'][@id='txtUsername']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='txtPassword'][@id='txtPassword']")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@value='LOGIN'][@id='btnLogin']")).click();
        String valid = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
        Assert.assertEquals(valid, "Username cannot be empty");

        //Assert.assertTrue(driver.findElement(By.xpath("//span[@id='spanMessage']")).isDisplayed());
    }

    @Test(priority = 5)
    public void emptyPassword(){
        driver.findElement(By.xpath("//input[@name='txtUsername'][@id='txtUsername']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='txtPassword'][@id='txtPassword']")).sendKeys("");
        driver.findElement(By.xpath("//input[@value='LOGIN'][@id='btnLogin']")).click();
        String valid = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
        Assert.assertEquals(valid, "Password cannot be empty");


    }

    @Test(priority = 6)

    public void dropDownMenu() throws InterruptedException {

        this.login();

            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(By.linkText("Admin"))).perform();
            driver.findElement(By.linkText("User Management")).click();
            driver.findElement(By.linkText("Users")).click();
        Thread.sleep(2000);
        String valid = driver.findElement(By.xpath("//*[@id=\"systemUser-information\"]/div[1]/h1")).getText();
            Thread.sleep(2000);
            Assert.assertEquals(valid,"System Users");



        }



    @Test(priority = 7)
    public void checkAllBoxes() throws InterruptedException {

        this.dropDownMenu();
        driver.findElement(By.xpath("//input[@type='checkbox'][@id='ohrmList_chkSelectAll']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='checkbox'][@id='ohrmList_chkSelectAll']")).isSelected(),true);



        /*

        driver.findElement(By.xpath("//input[@type='checkbox'][@value='3']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox'][@value='10']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox'][@value='4']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox'][@value='2']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox'][@value='9']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox'][@value='8']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox'][@value='5']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox'][@value='11']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox'][@value='7']")).click();
        */



    }

    @Test(priority = 8)

     public void resetButton() throws InterruptedException {
     this.checkAllBoxes();
     driver.findElement(By.xpath("//*[@id=\"resetBtn\"]")).click();
     Assert.assertEquals(driver.findElement(By.xpath("//input[@type='checkbox'][@id='ohrmList_chkSelectAll']")).isSelected(),false);
    }



    @Test(priority = 9)
    public void searchUserType() throws InterruptedException {

        this.dropDownMenu();

        String arr[] = {"All", "Admin", "ESS"};


        Select oSelect = new Select(driver.findElement(By.id("searchSystemUser_userType")));

        List<WebElement> dropdown = oSelect.getOptions();

        System.out.println(dropdown.size());

        for (int i = 0; i < dropdown.size(); i++) {

            Assert.assertEquals(arr[i], dropdown.get(i).getText());

        }


    }

    @Test(priority = 10)

        public void create_user() throws InterruptedException {

        this.dropDownMenu();

         driver.findElement(By.xpath("//input[@ id='btnAdd']")).click();

        // WebElement user_role = driver.findElement(By.id("systemUser_userType"));
        // Select type = new Select(user_role);
        // type.selectByValue("2");
         driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys(employeeName);
         driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys(username);
         driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys(passowrd);
         driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys(passowrd);
         Thread.sleep(2000);
         driver.findElement(By.name("btnSave")).click();
         Thread.sleep(2000);
         String badAss = driver.findElement(By.xpath(xpath)).getText(); //edit xpath
         Assert.assertEquals(badAss,username);/*


         /*String valid = driver.findElement(By.xpath("//div[@class='message success fadable']")).getText();
         Assert.assertEquals(valid,"Successfully Saved");*/

    }

    @Test(priority = 11)
    public void loginNewUser(){

        driver.findElement(By.xpath("//input[@name='txtUsername'][@id='txtUsername']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='txtPassword'][@id='txtPassword']")).sendKeys(passowrd);
        driver.findElement(By.xpath("//input[@value='LOGIN'][@id='btnLogin']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"welcome\"]")).isDisplayed());


    }

    @Test(priority = 12)
    public void editUser() throws InterruptedException {

        this.dropDownMenu();
        driver.findElement(By.xpath(xpath)).click(); //edit xpath
        String valid = driver.findElement(By.xpath("//*[@id=\"UserHeading\"]")).getText();
        Assert.assertEquals(valid,"Edit User");
    }




    @Test(priority = 13)

    public void disableUser() throws InterruptedException {
     Thread.sleep(2000);
     this.editUser();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Edit']")).click();
    WebElement disable = driver.findElement(By.id("systemUser_status"));
    Select type = new Select(disable);
    type.selectByValue("0");
    driver.findElement(By.xpath("//input[@value='Save']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//a[contains(@href,'#')][text()='Welcome Admin']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//a[contains(@href,'/index.php/auth/logout')][text()='Logout']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@name='txtUsername'][@id='txtUsername']")).sendKeys(username);
    driver.findElement(By.xpath("//input[@name='txtPassword'][@id='txtPassword']")).sendKeys(passowrd);
    driver.findElement(By.xpath("//input[@value='LOGIN'][@id='btnLogin']")).click();
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("//span[contains(@id,'spanMessage')][text()='Account disabled']")).isDisplayed());


    }



    @Test(priority = 14)

        public void searchUser() throws InterruptedException {
        this.dropDownMenu();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@value='Search']")).click();
        String valid = driver.findElement(By.xpath(xpath)).getText(); //edit the xpath
        Thread.sleep(2000);
        Assert.assertEquals(valid,username);


    }


    @Test(priority = 15)

    public void deleteUser() throws InterruptedException {
        this.searchUser();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='ohrmList_chkSelectAll']")).click();
        driver.findElement(By.xpath("//input[@id='btnDelete']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='button'][@class='btn']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@value='Search']")).click();
        Thread.sleep(2000);
        String valid = driver.findElement(By.xpath("//td[contains(@colspan,'5')][text()='No Records Found']")).getText();
        Thread.sleep(2000);
        Assert.assertEquals(valid,"No Records Found");




    }






        @AfterMethod
    public void tearDown(){

   driver.quit();
    }



}
