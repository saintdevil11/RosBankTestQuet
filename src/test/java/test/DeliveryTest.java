package test;

import hooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static steps.BaseSteps.*;
import static steps.LoginSteps.loginPageOpened;
import static steps.RegistrationSteps.*;
import static steps.StatusCheckSteps.checkStatusText;
import static steps.StatusCheckSteps.statusCheckPageOpened;

public class DeliveryTest extends WebHooks {

    @Test
    @Tag("1")
    @DisplayName("Авторизация тестового клиента")
    public void loginTestClient () {
        // Страница авторизации
        loginPageOpened();
        inputUID(utils.Configuration.getConfigurationValue("user.test"));
        inputPassword(utils.Configuration.getConfigurationValue("password.test"));
        clickOnLoginButton();
        // Страница проверки статуса
        statusCheckPageOpened();
        checkStatusText("Тест","Страна#");
    }

    @Test
    @Tag("2")
    @DisplayName("Регистрация и авторизация клиента")
    public void signInAndLogin () {
        // Страница авторизации
        loginPageOpened();
        clickOnHrefButton("Зарегистрироваться");
        // Страница Регистрации
        RegistrationPageOpened();
        inputUID("Тестович");
        inputName("Иван");
        inputCountry("Уругвай");
        inputPassword("123QAZ");
        clickOnRegistrationButton();
        // Страница авторизации
        loginPageOpened();
        inputUID("Тестович");
        inputPassword("123QAZ");
        clickOnLoginButton();
        // Страница проверки статуса
        statusCheckPageOpened();
        checkStatusText("Иван","Уругвай");
    }

    @Test
    @Tag("3")
    @DisplayName("Регистрация клиента и авторизация по sessionId (в версиях V2 и V3)")
    public void signInAndLoginV2V3 (){
        // Страница авторизации
        loginPageOpened();
        clickOnButton(utils.Configuration.getConfigurationValue("version"));
        clickOnHrefButton("Зарегистрироваться");
        // Страница Регистрации
        RegistrationPageOpened();
        inputUID("Тестович");
        inputName("Иван");
        inputCountry("Уругвай");
        inputPassword("123QAZ");
        setUpCheckBox("Создать идентификатор сессии автоматически", "true");
        setUpCheckBox("Создать идентификатор сессии автоматически", "false");
        generateSessionId();
        inputSessionId("sid");
        clickOnRegistrationButton();
        // Страница авторизации
        loginPageOpened();
        setUpCheckBox("Войти по идентификатору", "true");
        inputSessionId("sid");
        clickOnLoginButton();
        // Страница проверки статуса
        statusCheckPageOpened();
        checkStatusText("Иван","Уругвай");
    }

    @Test
    @Tag("4")
    @DisplayName("Авторизация тестового клиента и повторный вход по созданному sessionId (в версиях V2 и V3)")
    public void signInAndAgainLoginV2V3 (){
        // Страница авторизации
        loginPageOpened();
        clickOnButton(utils.Configuration.getConfigurationValue("version"));
        inputUID(utils.Configuration.getConfigurationValue("user.test"));
        inputPassword(utils.Configuration.getConfigurationValue("password.test"));
        clickOnLoginButton();
        // Страница проверки статуса
        statusCheckPageOpened();
        checkStatusText("Тест","Страна#");
        clickOnButton("Выйти из системы");
        // Страница авторизации
        loginPageOpened();
        copySessionId();
        setUpCheckBox("Войти по идентификатору", "true");
        inputSessionId("sid");
        clickOnLoginButton();
        // Страница проверки статуса
        statusCheckPageOpened();
        checkStatusText("Тест","Страна#");
    }
}
