package api;

import io.restassured.response.Response;
import models.AddBookRequest;
import lombok.DeleteBookRequest;



import static io.restassured.RestAssured.given;
import static specs.ApiTestSpec.*;

public class BookApi {

    public static void deleteAllBooks(Response getAuth) {
        given(deleteRequestSpec)
                .header("Authorization", "Bearer " + getAuth.path("token"))
                .when()
                .delete("BookStore/v1/Books?UserId=" + getAuth.path("userId"))
                .then()
                .spec(deleteResponseSpec)
                .extract();
    }

    public static void addBook(Response authResponse, String isbn) {
        AddBookRequest addBookModel = new AddBookRequest();
        AddBookRequest.CollectionInfo collectionInfo = new AddBookRequest.CollectionInfo();
        collectionInfo.setIsbn(isbn);
        addBookModel.getCollectionOfIsbns().add(collectionInfo);
        addBookModel.setUserId(authResponse.path("userId"));
        given(requestBookSpec)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(addBookModel)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(createResponseSpec);
    }

    public static void deleteBook(Response authResponse, String isbn) {
        DeleteBookRequest DeleteBookRequest = new DeleteBookRequest();
        DeleteBookRequest.setIsbn(isbn);
        DeleteBookRequest.setUserId(authResponse.path("userId"));
        given(requestBookSpec)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(DeleteBookRequest)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(deleteResponseSpec);
    }
}