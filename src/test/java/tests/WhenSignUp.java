package tests;

import io.cucumber.java.hu.De;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithDriver;
import net.thucydides.junit.annotations.Concurrent;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import steps.SignUpSteps;

import java.net.MalformedURLException;
import java.net.URL;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

@RunWith(SerenityRunner.class)
//@Concurrent(threads="4")
public class WhenSignUp {


    @Managed(driver="chrome")
     WebDriver driver;

  /*  @Before
    public  void startUp() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        //getDriver().manage().window().maximize();
        //driver.manage().window().maximize();
    }*/


    @Steps
    SignUpSteps steps;

   // @WithDriver("chrome")
    @Test
    @Title("Test1 -> Check LogOn failure")
    public void spotifyFailedSignUpCheck()  {
        steps.openSignUpPageStep();
        steps.fullRegistrationStep("test@ya.ru","test@ya.ru", "test@ya.ruPASSWORD",
                "testUser", 11, "05", 2012, true);

        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(steps.getH2HeaderValueStep()).as("Другая страница <> регистрации!").isEqualTo("Зарегистрируйтесь и слушайте бесплатно");
        softAssert.assertAll();
    }
}
//mvn -Dtest=WhenSignUp,WhenSignUp2 test
// mvn serenity:aggregate or  !!!!!!   mvn clean -Dtest=WhenSignUp verify !!!
//mvn verify -Dwebdriver.remote.url=http://localhost:4444/wd/hub -Dwebdriver.remote.driver=chrome
// -Dwebdriver.remote.os=WINDOWS -Dchrome.switches="--no-sandbox,--ignore-certificate-errors,--homepage=about:blank,--no-first-run"