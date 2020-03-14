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
class LoginRegisterControllerTest {

    private WebDriver webDriver;
    private String username;
    private String password;
    private String url;

    @Autowired
    public LoginRegisterControllerTest() {
        System.setProperty("webdriver.chrome.driver", "/home/hebel/IntelliJ_Projects/GitRepos/charity-project/src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        username = "randomUser";
        password = "randomPass";
        url = "http://localhost:8080/login";
    }

    @Test
    void shouldNotLetLogin() {
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

        Assertions.assertThat(webDriver.getPageSource().contains("Niepoprawna nazwa użytkownika i/lub hasło")).isTrue();
    }

    @After
    public void closeWeb() {
        webDriver.quit();
    }
}
