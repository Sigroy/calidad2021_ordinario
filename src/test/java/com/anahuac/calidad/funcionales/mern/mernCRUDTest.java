package com.anahuac.calidad.funcionales.mern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class mernCRUDTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "http://localhost:3000/";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void newUser() throws Exception {

        driver.get("http://localhost:3000/");


        driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();// "//div[@id='root']/div/div[2]/button"

        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("Sigfredo");

        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("sigfredoolmedo6c17@gmail.com");

        driver.findElement(By.name("age")).click();
        driver.findElement(By.name("age")).clear();
        driver.findElement(By.name("age")).sendKeys("21");

        driver.findElement(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]"))
                .click();
        driver.findElement(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::span[1]"))
                .click();
        driver.findElement(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]"))
                .click();

        pause(5000);

        String tag = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
        assertThat("Nice one!", is(tag));
    }

    @Test
    public void borrarUser() throws Exception {
        driver.get("http://localhost:3000");
        driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("sigfredoolmedo6c17");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("sigfredoolmedo6c17@gmail.com");
        driver.findElement(By.name("age")).click();
        driver.findElement(By.name("age")).clear();
        driver.findElement(By.name("age")).sendKeys("20");
        driver.findElement(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]"))
                .click();
        driver.findElement(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Female'])[1]/following::span[1]"))
                .click();
        driver.findElement(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]"))
                .click();
        driver.findElement(By.xpath("/html/body/div[2]/div/i")).click();
        pause(1000);
        driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();
        pause(1000);
        String tag = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody")).getText();
        assertThat("", is(tag));
    }

    @Test
    public void actualizarUser() throws Exception {

        driver.get("http://localhost:3000/");

        driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[1]")).click();

        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        pause(2000);
        driver.findElement(By.name("email")).sendKeys("sigfredoolmedo6c17@gmail.com");

        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();

        pause(5000);

        String tag = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
        assertThat("Nice one!", is(tag));

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private void pause(long mils) {
        try {
            Thread.sleep(mils);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}