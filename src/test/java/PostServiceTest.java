
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;



public class PostServiceTest {

    @Test
    public void displayAllPostStatusCode200() {
        given().when().get("/posts").then().statusCode(200);
    }

    @Test
    public void displayPostWithoutUserIdStatusCode200() {
        given().when().get("/posts/withoutId").then().statusCode(200);
    }

    @Test
    public void postTitleIsntExistInDBStatusCode204() {
        given().pathParam("postTitle", "New Title").when().get("posts/{postTitle}").then().statusCode(204);
    }

    @Test
    public void wrongAuthorPostStatusCode401() {
        given(given().body(" ").pathParam("id", 1).header("userid", 10)).when().put("/posts/body/{id}").then().statusCode(401);
    }

    @Test
    public void correctlyChangePostTitleStatusCode200() {
        given(given().body(" ").pathParam("id", 1).header("userid", 1)).when().put("/posts/title/{id}").then().statusCode(200);
    }

    @Test
    public void correctlyUpdateDBByUserStatusCode200(){
        given().when().put("/rest").then().statusCode(200);
    }

    @Test
    public void WrongAuthorPostToDeleteStatusCode401(){
        given().pathParam("id",1).header("userid",10).when().delete("/posts/{id}").then().statusCode(401);
    }
    @Test
    public void PostToDeleteDoesntExistStatusCode204(){
        given().pathParam("id",111).header("userid",10).when().delete("/posts/{id}").then().statusCode(204);
    }







}
