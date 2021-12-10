package hooks;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static PageElements.BasePage.getButton;
import static PageElements.LoginPage.loginPageIsOpened;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Класс с настройками драйвера.
 * */
public class WebHooks {

    @BeforeAll
    public static void setDriverFromProps() {
        Configuration.startMaximized=true;
        Configuration.pageLoadTimeout = 120000;
        Configuration.timeout=15000;
        //Подключаем драйвер напрямую из test.properties
        String webDriverLocation = utils.Configuration.getConfigurationValue("webdriverlocation");
        //Следуем по условию запуска на selenoid, есть строка в проперти закомменчена то запуск на локальном драйвере
        if (utils.Configuration.getConfigurationValue("remote.url") != null)
            Configuration.remote = utils.Configuration.getConfigurationValue("remote.url");
        //Указание selenide с каким браузером работать и с каким драйвером, фуллскрин драйвера
        //Нужно это только если не интернета. Selenide сам из интернета
        if (webDriverLocation != null) {
            System.setProperty("webdriver.chrome.driver", webDriverLocation);
            System.setProperty("selenide.browser", "Chrome");
        }
    }

    /**
     * Метод аллюр менеджер: для отображения в аллюре селенидовских шагов при наличии в проекте параллелизации
     */
    @BeforeAll
    public static void allureSubThreadParallel(){
        String listenerName = "AllureSelenide";

        if(!(SelenideLogger.hasListener(listenerName)))
            SelenideLogger.addListener(listenerName,
                    new AllureSelenide().
                            screenshots(true).
                            savePageSource(false));
    }

    @BeforeEach()
    public void start() {
        open(utils.Configuration.getConfigurationValue("url.service"));
        sleep(3000);
        loginPageIsOpened();
        getButton(utils.Configuration.getConfigurationValue("version")).click();
    }


    @AfterEach()
    public void driverClose() {
        WebDriverRunner.closeWebDriver();
    }
}
