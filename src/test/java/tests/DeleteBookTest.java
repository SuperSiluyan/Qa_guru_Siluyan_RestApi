package tests;

import api.AuthApi;
import api.BookApi;
import helpers.WithLogin;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pageObjects.ProfilePage;
import testBases.TestBaseDemoQa;
import testBases.TestBaseLocal;

import static io.qameta.allure.Allure.step;


public class DeleteBookTest extends TestBaseDemoQa {
    AuthApi auth = new AuthApi();
    Response authResponse = auth.getAuth();
    BookApi actionWithBook = new BookApi();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @Tag("Configurate")
    @DisplayName("Delete book from profile")
    @WithLogin
    void deleteBookFromProfileTest() {


        step("Delete all books", () -> {
            actionWithBook.deleteAllBooks(authResponse);
        });

        step("Add book", () -> {
            actionWithBook.addBook(authResponse, "9781449325862");
        });

        step("Delete book", () -> {
            actionWithBook.deleteBook(authResponse, "9781449325862");
        });

        step("Check delete book", () -> {
            profilePage.profilePageOpen()
                    .checkDeletedBook();
        });

    }
}
