package tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.SignUpPage;
import steps.SignUpSteps;

import java.net.MalformedURLException;
import java.net.URL;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
//@Concurrent(threads="4")
public class WhenSignUp2 {

    @Managed(driver="chrome")
    WebDriver driver;

 /*   @Before
    public  void startUp() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
         //getDriver().manage().window().maximize();
       // driver.manage().window().maximize();
    }*/

    @Steps
    SignUpSteps steps;


    @Test
    @Title("Test2 -> Check birth year failure error message")
    public void spotifyFailedSignUpCheckTextErrors() {
        steps.openSignUpPageStep();
        steps.fullRegistrationStep("test@ya.ru","test@ya.ru", "test@ya.ruPASSWORD",
                "testUser", 11, "05", 2012, true);

        assertThat(steps.isErrorVisibleStep("Укажите действительный день месяца.")).as("Такого текста ошибки не должно отображаться!").isFalse();
        assertThat(steps.getErrorByNumberStep(0)).as("Ошибки не совпадают!").isEqualTo("Вы не достигли возраста, с которого можно пользоваться сервисом Spotify.");
       // Assertions.assertEquals(signUpPage.getErrorByNumber(0), "Вы не достигли возраста, с которого можно пользоваться сервисом Spotify.", "Ошибки не совпадают!");
       //Assertions.assertFalse(signUpPage.isErrorVisible("Укажите действительный день месяца."));
    }


}