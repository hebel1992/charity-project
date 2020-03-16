package pl.coderslab.charityproject.controllers;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginTest {

    //GIVEN

    private WebDriver webDriver;
    private String username;
    private String password;
    private String url;

    @Autowired
    public LoginTest() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        url = "http://localhost:8080/login";
    }

    @Test
    void shouldNotLetLogin() {
        //WHEN

        username = "wrongName";
        password = "wrongPassword";


        webDriver.get(url);
        webDriver.findElement(By.partialLinkText("Zaloguj")).click();

        WebElement loginName = webDriver.findElement(By.name("username"));
        loginName.clear();
        loginName.sendKeys(username);

        WebElement loginPassword = webDriver.findElement(By.name("password"));
        loginPassword.clear();
        loginPassword.sendKeys(password);

        WebElement loginButton = webDriver.findElement(By.name("login"));
        loginButton.click();

        //THEN

        Assertions.assertThat(webDriver.getPageSource().contains("Niepoprawna nazwa użytkownika i/lub hasło")).isTrue();
    }


    @Test
    void shouldLetLogin() {
        //WHEN

        username = "testuser";
        password = "testuser";


        webDriver.get(url);
        webDriver.findElement(By.partialLinkText("Zaloguj")).click();

        WebElement loginName = webDriver.findElement(By.name("username"));
        loginName.clear();
        loginName.sendKeys(username);

        WebElement loginPassword = webDriver.findElement(By.name("password"));
        loginPassword.clear();
        loginPassword.sendKeys(password);

        WebElement loginButton = webDriver.findElement(By.name("login"));
        loginButton.click();

        //THEN

        Assertions.assertThat(webDriver.getPageSource().contains("Niepoprawna nazwa użytkownika i/lub hasło")).isFalse();
    }

    @After
    public void closeWeb() {
        webDriver.quit();
    }
}
