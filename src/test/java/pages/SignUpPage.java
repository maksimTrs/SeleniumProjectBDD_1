package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@DefaultUrl("https://www.spotify.com/ru-ru/signup")
public class SignUpPage extends PageObject {


    private By h2HeaderData = By.xpath("//div[@id='__next']//div/h2");

    /*    @FindBy(xpath = "//input[@id = 'email']")
        private WebElement emailField;*/
    private By emailField = By.xpath("//input[@id = 'email']");

    private By emailConfirmField = By.cssSelector("input#confirm[name='confirm']");

    private By passwordField = By.cssSelector("div > input[type='password']");

    private By nameField = By.cssSelector("input#displayname");

    private By dayOfBirth = By.cssSelector("input#day");

    private By monthDropDown = By.xpath("//select[@id='month']");


    String monthDropDownOption = "//select[@id='month']/option[@value = '%s']"; // May = 05.
/*    @FindBy(xpath = "//select[@id='month']/option[@value = '05']") // May
    private WebElement monthDropDownOption;*/

    private By yearOfBirth = By.cssSelector("input#year");

    private By maleGenderRadioButton = By.xpath("//input[@id='gender_option_male']/following::label[1]");

    private By femaleGenderRadioButton = By.cssSelector("input#gender_option_male");

    private By termsConditionsCheckbox = By.xpath("//input[@id='terms-conditions-checkbox']/following::label[1]");

    // @FindBy(xpath = "//div/label[@id='recaptcha-anchor-label']") //div[@id='rc-anchor-container']//div/span[@id='recaptcha-anchor']
    private By recaptchaCheckBox = By.xpath("//div/label[@id='recaptcha-anchor-label']");


    private By submitButton = By.xpath("//button[@type='submit']");

    /* @FindBy(xpath = "//div[@aria-label='Значок ошибки' and string-length(text())>0] | //div[@aria-label='Значок ошибки']/span[string-length(text())>0]")
       private List<WebElement> errorForEmptyFields;  // 10 fields*/
    private By errorForEmptyFields = By.xpath("//div[@aria-label='Значок ошибки' and string-length(text())>0] | //div[@aria-label='Значок ошибки']/span[string-length(text())>0]");

    String errorByText = "//div[@aria-label='Значок ошибки'][text()= '%s']";
    //Введите адрес электронной почты.   <<>>   Введите пароль.




    public String getH2HeaderValue() {
       // return h2HeaderData.getText();
       return find(h2HeaderData).getText();
    }

    public SignUpPage addTextToEmailField(String email) {
        find(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage  addTextToEmailConfirmField(String email) {
        find(emailConfirmField).sendKeys(email);
        return this;
    }

    public SignUpPage  addTextToPasswordField(String password) {
        find(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage  addTextToNameField(String name) {
        find(nameField).sendKeys(name);
        return this;
    }

    public SignUpPage  setDayOfBirth(int birthDay) {
       find(dayOfBirth).sendKeys(String.valueOf(birthDay));
        return this;
    }

    public SignUpPage setMonth(String birthDay) {
        find(monthDropDown).click();
        /*waitForCondition().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(String.format(monthDropDownOption, birthDay)))).click();*/
        find(By.xpath(String.format(monthDropDownOption, birthDay))).waitUntilVisible().click();
        return this;
    }

    public SignUpPage  setYearOfBirth(int birthYear) {
        find(yearOfBirth).sendKeys(String.valueOf(birthYear));
        return this;
    }

    public SignUpPage setMaleGenderRadioButton() {
        find(maleGenderRadioButton).click();
        return this;
    }

    public SignUpPage setFemaleGenderRadioButton() {
        find(femaleGenderRadioButton).click();
        return this;
    }

    public SignUpPage setTermsConditionsCheckbox(boolean value) {
        if (!find(termsConditionsCheckbox).isSelected() == value)
            find(termsConditionsCheckbox).click();
        return this;
    }


    public void clickSubmitButton() {
        evaluateJavascript("window.scrollBy(0,250)");
        find(submitButton).click();
   /*     String ss = driver.findElement(By.cssSelector("button[type='submit'] > div")).getText();
        System.out.println(ss);*/
    }

    public List<WebElementFacade> getErrorFieldsList() {
        //return driver.findElements(By.xpath(errorForEmptyFields.toString()));
        return findAll(errorForEmptyFields);
    }

    public String getErrorByNumber(int number) {
        return getErrorFieldsList().get(number).getText();
    }

    public boolean isErrorVisible(String message) {
        return findAll(By.xpath(String.format(errorByText, message))).size() > 0
                && findAll(By.xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }

    public void closeCookieMessage() {
       // waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='onetrust-group-container']")));
        find(By.xpath("//div[@id='onetrust-group-container']")).waitUntilVisible();
        find(By.xpath("//div[@id='onetrust-close-btn-container']/button")).click();
    }


    public void successLogOn(String email, String confirmEmail, String password, String userName, int dayBirth, String monthBirth,
                             int yearBirth, boolean termsCondition) {
        closeCookieMessage();
        addTextToEmailField(email);
        addTextToEmailConfirmField(confirmEmail);
        addTextToPasswordField(password);
        addTextToNameField(userName);
        setDayOfBirth(dayBirth);
        setMonth(monthBirth);
        setYearOfBirth(yearBirth);
        setMaleGenderRadioButton();
        setTermsConditionsCheckbox(termsCondition);
        // setRecaptchaCheckBox();
        clickSubmitButton();
        //return new LogOnPage();
    }
}