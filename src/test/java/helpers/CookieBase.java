package helpers;

import api.AuthApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CookieBase implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        AuthApi loginWithApi = new AuthApi();
        Response cookie = loginWithApi.getAuth();
        open("images/zero-step.jpeg");
        getWebDriver().manage().addCookie(new Cookie("userID", cookie.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", cookie.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", cookie.path("token")));
    }
}