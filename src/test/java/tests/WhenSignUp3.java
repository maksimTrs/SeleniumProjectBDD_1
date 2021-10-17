package tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import steps.SignUpSteps;

import java.net.MalformedURLException;
import java.net.URL;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
//@Concurrent(threads="4")
public class WhenSignUp3 {

    @Managed(driver="chrome")
    WebDriver driver;

 /*   @Before
    public  void startUp() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        //getDriver().manage().window().maximize();
      //  driver.manage().window().maximize();
    }*/

    @Steps
    SignUpSteps steps;



    @Test
    @Title("Test3 -> Check all errors for all fields, except birthday fields")
    public void spotifyFailedCountOfErrors() {
        steps.openSignUpPageStep();
        steps.closeCookieMessageStep();
        steps.addBirthdayDataStep(11, "11", 1989);
        steps.submitActionStep();

        assertThat(steps.getErrorFieldsListStep()).as("Такого текста ошибки не должно отображаться!").isEqualTo(7);
        assertThat(steps.getErrorByNumberStep(4)).as("Ошибки не совпадают!").isEqualTo("Выберите свой пол.");
        assertThat(steps.isErrorVisibleStep("Укажите действительный день месяца.")).as("Такого текста ошибки не должно отображаться!").isFalse();
        assertThat(steps.isErrorVisibleStep("Выберите месяц.")).as("Такого текста ошибки не должно отображаться!").isFalse();
        assertThat(steps.isErrorVisibleStep("Укажите действительный год.")).as("Такого текста ошибки не должно отображаться!").isFalse();
    }

    @Test
    @Pending
    @Title("Fake test method")
    public void pendingTest() {
        steps.openSignUpPageStep();
        steps.closeCookieMessageStep();
        steps.addBirthdayDataStep(11, "11", 1989);
        steps.submitActionStep();

        assertThat(steps.isErrorVisibleStep("Выберите месяц.")).as("Такого текста ошибки не должно отображаться!").isTrue();

    }
}