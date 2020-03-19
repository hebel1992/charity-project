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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
class FormTest {

    //GIVEN

    private WebDriver webDriver;
    private String username;
    private String password;
    private String street;
    private String city;
    private String zipCode;
    private String phone;
    private String date;
    private String time;
    private String url;

    @Autowired
    public FormTest() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        webDriver = new ChromeDriver(options);
        url = "http://localhost:8080/login";
    }

    @Test
    void shouldSendForm() {
        //WHEN

        username = "testuser";
        password = "testuser";

        street = "Mała 5";
        city = "Pławy";
        zipCode = "43-123";
        phone = "456123098";
        date = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(LocalDate.now().plus(5, ChronoUnit.DAYS));
        time = "1600";


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

        //form step 1

        List<WebElement> chooseCategory = webDriver.findElements((By.name("categoryCheckbox")));
        chooseCategory.get(2).click();
        chooseCategory.get(5).click();
        WebElement nextButton1 = webDriver.findElement(By.name("next-button1"));
        nextButton1.click();

        //form step 2

        WebElement inputQuantity = webDriver.findElement(By.name("quantity"));
        inputQuantity.clear();
        inputQuantity.sendKeys("10");
        WebElement nextButton2 = webDriver.findElement(By.name("next-button2"));
        nextButton2.click();

        //form step 3

        List<WebElement> chooseInstitution = webDriver.findElements((By.name("checkbox-radio")));
        chooseInstitution.get(4).click();
        WebElement nextButton3 = webDriver.findElement(By.name("next-button3"));
        nextButton3.click();

        //form step 4

        WebElement streete = webDriver.findElement(By.name("street"));
        streete.clear();
        streete.sendKeys(street);

        WebElement citye = webDriver.findElement(By.name("city"));
        citye.clear();
        citye.sendKeys(city);

        WebElement zipCodee = webDriver.findElement(By.name("zipCode"));
        zipCodee.clear();
        zipCodee.sendKeys(zipCode);

        WebElement phonee = webDriver.findElement(By.name("phoneNumber"));
        phonee.clear();
        phonee.sendKeys(phone);

        WebElement datee = webDriver.findElement(By.name("pickUpDate"));
        datee.clear();
        datee.sendKeys(date.toString());

        WebElement timee = webDriver.findElement(By.name("pickUpTime"));
        timee.clear();
        timee.sendKeys(time);

        WebElement nextButton4 = webDriver.findElement(By.name("next-button4"));
        nextButton4.click();

        WebElement confirmButton = webDriver.findElement(By.name("confirmButton"));
        confirmButton.click();

        //THEN

        Assertions.assertThat(webDriver.getPageSource().contains("Twoje zgłoszenie zostało wysłane.")).isTrue();

    }


    @After
    public void closeWeb() {
        webDriver.quit();
    }
}
