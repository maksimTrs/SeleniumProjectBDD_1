package steps;

import net.thucydides.core.annotations.Step;
import pages.SignUpPage;

public class SignUpSteps {
     SignUpPage signUpPage;

    @Step
    public void openSignUpPageStep() {
        signUpPage.open();
    }

    @Step
    public void addEmailDataStep(String email) {
        signUpPage.addTextToEmailField(email);
        signUpPage.addTextToEmailConfirmField(email);
    }

    @Step("Fill Birthday Fields: day {0}, month {1}, year {2}")
    public void addBirthdayDataStep(int day, String month, int year) {
        signUpPage.setDayOfBirth(day);
        signUpPage.setMonth(month);
        signUpPage.setYearOfBirth(year);
    }

    @Step
    public void addPasswordDataStep(String password){
        signUpPage.addTextToPasswordField(password);
    }

    @Step
    public void addNameDataStep(String name){
        signUpPage.addTextToNameField(name);
    }

    @Step
    public void activateRadioAndCheckboxButtonsStep(boolean clickOTermsConditionsCheckbox) {
        signUpPage.setFemaleGenderRadioButton();
        signUpPage.setTermsConditionsCheckbox(clickOTermsConditionsCheckbox);
    }

    @Step
    public void submitActionStep() {
        signUpPage.clickSubmitButton();
    }

    @Step
    public void closeCookieMessageStep() {
        signUpPage.closeCookieMessage();
    }

    @Step
    public void fullRegistrationStep(String email, String confirmEmail, String password, String userName, int dayBirth, String monthBirth,
                                 int yearBirth, boolean termsCondition) {
        signUpPage.successLogOn(email, confirmEmail, password, userName, dayBirth, monthBirth, yearBirth, termsCondition);
    }

    @Step
    public String getH2HeaderValueStep() {
       return signUpPage.getH2HeaderValue();
    }

    @Step
    public boolean isErrorVisibleStep(String message) {
        return signUpPage.isErrorVisible(message);
    }

    @Step
    public String getErrorByNumberStep(int number) {
        return signUpPage.getErrorByNumber(number);
    }


    @Step
    public int getErrorFieldsListStep() {
      return signUpPage.getErrorFieldsList().size();
    }
}

