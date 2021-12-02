package com.anahuac.calidad.bdd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginSteps {

    private WebDriver driver;

    @Given("Browser is open")
    public void browser_is_open() {
        WebDriverManager.chromedriver().setup();

        System.out.println("Inside Step-browser is open");
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project path is :" + projectPath);

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

        // driver.manage().window().maximize();
    }

    @And("user is on login page")
    public void user_is_on_login_page() {
        driver.navigate().to("https://example.testproject.io/web/");
    }

    @When("user enter username and password")
    public void user_enter_username_and_password() throws InterruptedException {
        driver.findElement(By.id("name")).sendKeys("Eduardo");
        driver.findElement(By.id("Password")).sendKeys("12345");

        Thread.sleep(2000);
    }

    @And("user clicks on login button")
    public void user_clicks_on_login_button() {
        driver.findElement(By.id("login")).click();
    }

    @Then("The user is navigated to the home page")
    public void the_user_is_navigated_to_the_home_page() throws InterruptedException {

        if (driver.findElement(By.id("logout")).isDisplayed()) {

            System.out.println("Caso: usuario válido");
            Thread.sleep(2000);

            driver.quit();
        } else {
            System.out.println("Caso: credenciales invalidas");

            Thread.sleep(2000);

            String noPwd = driver.findElement(By.xpath("/html/body/div[2]/div[1]/form/div[2]/div/div[2]")).getText();
            assertThat("Password is invalid", is(noPwd));

            driver.quit();
        }

    }

}