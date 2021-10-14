package tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.SignUpSteps;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenSignUp3 {

    @Before
    public void startUp() {
        getDriver().manage().window().maximize();
    }

    @Steps
    SignUpSteps steps;

    @Managed//(driver = "chrome")
    WebDriver driver;

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