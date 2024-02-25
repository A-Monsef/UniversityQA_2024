package com.hiberus.ejercicios.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginSuiteTest {

    public static WebDriver driver;

    public static WebDriverWait wait;

    @Before
    public void setUp() {
        //Paso0
        WebDriverManager.chromedriver().setup(); // Cargar Chromedriver

        driver= new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10, 500);
    }

    @Test
    public void loginTest() {
    driver.get("https://www.saucedemo.com");
    WebElement inputName = driver.findElement(By.id("user-name"));
    inputName.sendKeys("standard_user");
    WebElement inputPassword = driver.findElement(By.id("password"));
    inputPassword.sendKeys("secret_sauce");
    WebElement button = driver.findElement(By.id("login-button"));
    button.click();
    String ExpectedData = "https://www.saucedemo.com/inventory.html";
    String ActualData = driver.getCurrentUrl();
    if(ActualData.equals(ExpectedData)){
        System.out.println("Valid Test");
    }else {
        System.out.println("Invalid Test. " + "Current Value " + ActualData + "\n" + "and the expected value is: " + ExpectedData);
    }

    }

    @Test
    public void loginIncorrectTest() {
        driver.get("https://www.saucedemo.com");
        WebElement inputName = driver.findElement(By.id("user-name"));
        inputName.sendKeys("problem_user");
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");
        WebElement button = driver.findElement(By.id("login-button"));
        button.click();
        String ExpectedData = "https://www.saucedemo.com/inventory.html";
        String ActualData = driver.getCurrentUrl();
        if(ActualData.equals(ExpectedData)){
            System.out.println("Valid Test");
        }else {
            System.out.println("Invalid Test. " + "Current Value " + ActualData + "\n" + "and the expected value is: " + ExpectedData);
        }

    }

    @After
    public void tearDown() {
        driver.close();
    }

}
