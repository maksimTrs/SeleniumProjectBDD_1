package tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import steps.SignUpSteps;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

@RunWith(SerenityRunner.class)
public class WhenSignUp {

    @Before
    public void startUp() {
        getDriver().manage().window().maximize();
    }

    @Steps
    SignUpSteps steps;

    @Managed//(driver = "chrome")
    WebDriver driver;

    @Test
    @Title("Test1 -> Check LogOn failure")
    public void spotifyFailedSignUpCheck() {
        steps.openSignUpPageStep();
        steps.fullRegistrationStep("test@ya.ru","test@ya.ru", "test@ya.ruPASSWORD",
                "testUser", 11, "05", 2012, true);

        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(steps.getH2HeaderValueStep()).as("Другая страница <> регистрации!").isEqualTo("Зарегистрируйтесь и слушайте бесплатно");
        softAssert.assertAll();
    }
}
//mvn -Dtest=WhenSignUp,WhenSignUp2 test
// mvn serenity:aggregate or  !!!!!!   mvn clean verify !!!