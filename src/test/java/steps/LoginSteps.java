package steps;

import PageElements.LoginPage;
import io.qameta.allure.Step;

public class LoginSteps extends LoginPage {

    @Step("Открыта страница Авторизации")
    public static void loginPageOpened(){
        spinerWait();
        loginPageIsOpened();
    }
}
