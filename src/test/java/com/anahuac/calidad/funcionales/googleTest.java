package com.anahuac.calidad.funcionales;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class googleTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testBbusqueda() throws Exception {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("covid 2021");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.findElement(
                By.xpath("//div[@id='kp-wp-tab-overview']/div[5]/div/div[2]/div/div/div/div/div/div/div/div/a/h3"))
                .click();
        assertEquals("Coronavirus – gob.mx", driver.getTitle());
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