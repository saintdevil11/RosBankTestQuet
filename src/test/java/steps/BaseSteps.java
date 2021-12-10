package steps;

import PageElements.BasePage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import stash.Stash;

import java.util.UUID;

import static com.codeborne.selenide.Selenide.$x;
import static hooks.Allerts.checkAlert;

public class BaseSteps extends BasePage {

    @Step("Нажимаем на кнопку Войти/Подключиться")
    public static void clickOnLoginButton() {
        getLoginButton().should(Condition.visible).click();
    }

    @Step("Нажимаем на кнопку {name}")
    public static void clickOnButton(String name) {
        getButton(name).should(Condition.visible).click();
    }

    @Step("Нажимаем на ссылку {name}")
    public static void clickOnHrefButton(String name) {
        grtHrefButton(name).should(Condition.visible).click();
    }

    @Step("Вводим идентификатор {uid}")
    public static void inputUID(String uid) {
        $x(inputUid).should(Condition.visible).click();
        checkAlert();
        $x(inputUid).setValue(uid);
    }

    @Step("Вводим пароль {pass}")
    public static void inputPassword(String pass) {
        $x(inputPass).click();
        checkAlert();
        $x(inputPass).setValue(pass);
    }

    @Step("Вводим идентификатор сессии")
    public static void inputSessionId(String sid) {
        $x(inputSId).click();
        if (sid.equals("") | sid.equals("sid")) {
            $x(inputSId).sendKeys(Stash.get(sid).toString());
        } else {
            $x(inputSId).sendKeys(sid);
        }
    }

    @Step("Копируем идентификатор сессии")
    public static void copySessionId() {
        String sid = $x(inputSId).getValue();
        Stash.put("sid", sid);
    }

    @Step("Генерируем Session ID и сохраняем в STASH")
    public static void generateSessionId() {
        String sid = UUID.randomUUID().toString();
        Stash.put("sid", sid);
    }

    @Step("Устанавливаем чек-бокс {name} в положение {condition}")
    public static void setUpCheckBox(String name, String condition) {
        setCheckBox(name, condition);
    }
}
