package Azumi.org.Post;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @Autowired
    PostService pService;

    @GetMapping("/posts")
    public ResponseEntity displayPostsId() throws JsonProcessingException {
        return pService.displayPostsId();
    }
    // display posts by title
    @GetMapping("/posts/{postTitle}")
    public ResponseEntity displayPostsByTitle(@PathVariable("postTitle") String postTitle) throws JsonProcessingException {
            return pService.displayPostsByTitle(postTitle);
    }

    // display all without author id
    @GetMapping("/posts/withoutId")
    public ResponseEntity displayPostsWithoutId() throws JsonProcessingException {
        return pService.displayPostsWithoutId();
    }

    // edit posts Title by author
    @PutMapping("/posts/title/{id}")
    public ResponseEntity editPostTitle(@RequestBody String newTitle, @PathVariable("id") int idPost,
                                        @RequestHeader("userid") int userId) throws JsonProcessingException{
        return pService.editPostTitle(newTitle, idPost, userId);
    }

    //edit postBody by author
    @PutMapping("/posts/body/{id}")
    public ResponseEntity editPostBody(@RequestBody String newBody, @PathVariable("id") int idPost,
                                       @RequestHeader("userid") int userId) throws JsonProcessingException{
        return pService.editPostBody(newBody, idPost, userId);
    }

    @PutMapping("/rest")
    public ResponseEntity update(){
        return pService.update();
    }

    // delete post by author
    @DeleteMapping("/posts/{id}")
    public ResponseEntity deletePost(@PathVariable("id") int idPost, @RequestHeader("userid") int userId) {
        return pService.deletePost(idPost, userId);
    }








}
