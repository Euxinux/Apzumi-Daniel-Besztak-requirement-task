import org.junit.Test;

import static io.restassured.RestAssured.given;
public class PostServiceTest {

    @Test
    public void displayAllPost_OK_StatusCode200() {
        given().when().get("/posts").then().statusCode(200);
    }

    @Test
    public void displayPostWithoutUserId_OK_StatusCode200() {
        given().when().get("/posts/withoutId").then().statusCode(200);
    }

    @Test
    public void editPostTitle_PostIsntExist_StatusCode204() {
        given().pathParam("postTitle", "New Title").when().get("posts/{postTitle}").then().statusCode(204);
    }

    @Test
    public void editPostBody_WrongAuthorPost_StatusCode401() {
        given(given().body(" ").pathParam("id", 1).header("userid", 10)).when().put("/posts/body/{id}").then().statusCode(401);
    }

    @Test
    public void editPostBody_CorrectlyAuthor_StatusCode200() {
        given(given().body(" ").pathParam("id", 1).header("userid", 1)).when().put("/posts/title/{id}").then().statusCode(200);
    }

    @Test
    public void updateDB_CorrectUpdate_StatusCode200(){
        given().when().put("/rest").then().statusCode(200);
    }

    @Test
    public void deletePost_WrongAuthorPost_StatusCode401(){
        given().pathParam("id",1).header("userid",10).when().delete("/posts/{id}").then().statusCode(401);
    }
    @Test
    public void deletePost_PostDoesntExist_StatusCode204(){
        given().pathParam("id",111).header("userid",10).when().delete("/posts/{id}").then().statusCode(204);
    }







}
