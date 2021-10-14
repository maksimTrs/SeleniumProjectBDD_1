package tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.SignUpPage;
import steps.SignUpSteps;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenSignUp2 {
    @Steps
    SignUpSteps steps;

    @Managed
    WebDriver driver;

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