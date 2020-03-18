package pl.coderslab.charityproject.controllers;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegisterTest {

    //GIVEN

    private WebDriver webDriver;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password1;
    private String password2;
    private String url;

    @Autowired
    public RegisterTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        webDriver = new ChromeDriver(options);
        url = "http://localhost:8080/register";
    }

    @Test
    void shoulRegisterUser() {
        //WHEN

        username = "anotheruser";
        firstName = "Bartek";
        lastName = "Kubiak";
        email = "b.kub@onet.pl";
        password1 = "Testuser123!";
        password2 = "Testuser123!";


        webDriver.get(url);
        webDriver.findElement(By.partialLinkText("Załóż konto")).click();

        WebElement loginName = webDriver.findElement(By.name("username"));
        loginName.clear();
        loginName.sendKeys(username);

        WebElement firstNamee = webDriver.findElement(By.name("firstName"));
        firstNamee.clear();
        firstNamee.sendKeys(firstName);

        WebElement lastNamee = webDriver.findElement(By.name("lastName"));
        lastNamee.clear();
        lastNamee.sendKeys(lastName);

        WebElement emaile = webDriver.findElement(By.name("email"));
        emaile.clear();
        emaile.sendKeys(email);

        WebElement passworde = webDriver.findElement(By.name("password"));
        passworde.clear();
        passworde.sendKeys(password1);

        WebElement passworde2 = webDriver.findElement(By.name("password2"));
        passworde2.clear();
        passworde2.sendKeys(password2);

        WebElement registerButton = webDriver.findElement(By.name("create"));
        registerButton.click();

        //THEN

        Assertions.assertThat(webDriver.getPageSource().contains("Konto zostało utworzone.")).isTrue();
    }

    @Test
    void shouldNotLetRepeatUsernameAndPassword() {
        //WHEN

        username = "testuser";
        email = "b.kowal@wp.pl";

        webDriver.get(url);
        webDriver.findElement(By.partialLinkText("Załóż konto")).click();

        WebElement loginName = webDriver.findElement(By.name("username"));
        loginName.clear();
        loginName.sendKeys(username);

        WebElement emaile = webDriver.findElement(By.name("email"));
        emaile.clear();
        emaile.sendKeys(email);

        WebElement registerButton = webDriver.findElement(By.name("create"));
        registerButton.click();

        //THEN

        Assertions.assertThat(webDriver.getPageSource().contains("nazwa zajęta")).isTrue();
        Assertions.assertThat(webDriver.getPageSource().contains("email już istnieje")).isTrue();
    }

    @Test
    void shoulNotRegisterUser() {
        //WHEN

        username = "usr";
        firstName = "as";
        lastName = "M";
        email = "b.asd.pl";
        password1 = "Tes";
        password2 = "asdfaq";


        webDriver.get(url);
        webDriver.findElement(By.partialLinkText("Załóż konto")).click();

        WebElement loginName = webDriver.findElement(By.name("username"));
        loginName.clear();
        loginName.sendKeys(username);

        WebElement firstNamee = webDriver.findElement(By.name("firstName"));
        firstNamee.clear();
        firstNamee.sendKeys(firstName);

        WebElement lastNamee = webDriver.findElement(By.name("lastName"));
        lastNamee.clear();
        lastNamee.sendKeys(lastName);

        WebElement emaile = webDriver.findElement(By.name("email"));
        emaile.clear();
        emaile.sendKeys(email);

        WebElement passworde = webDriver.findElement(By.name("password"));
        passworde.clear();
        passworde.sendKeys(password1);

        WebElement passworde2 = webDriver.findElement(By.name("password2"));
        passworde2.clear();
        passworde2.sendKeys(password2);

        WebElement registerButton = webDriver.findElement(By.name("create"));
        registerButton.click();

        //THEN

        Assertions.assertThat(webDriver.getPageSource().contains("3-15 znaków")).isTrue();
        Assertions.assertThat(webDriver.getPageSource().contains("2-15 znaków")).isTrue();
        Assertions.assertThat(webDriver.getPageSource().contains("8-15 znaków")).isTrue();
        Assertions.assertThat(webDriver.getPageSource().contains("nieprawidłowy format email")).isTrue();
        Assertions.assertThat(webDriver.getPageSource().contains("musi zawierać: małą litere, dużą litere, cyfrę, znak specjalny")).isTrue();

    }

    @After
    public void closeWeb() {
        webDriver.quit();
    }
}
