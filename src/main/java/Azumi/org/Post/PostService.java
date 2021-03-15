package Azumi.org.Post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PostRepository postRepository;

     // display all posts
    @GetMapping("/posts")
    public ResponseEntity displayPostsId () throws JsonProcessingException {
        List<Post> allPosts = postRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(allPosts));
    }
    // display posts by title
    @GetMapping("/posts/{postTitle}")
    public ResponseEntity displayPostsByTitle(@PathVariable("postTitle") String postTitle) throws JsonProcessingException {
        List<Post> byPostTitle = postRepository.findByPostTitle(postTitle);
        if (byPostTitle.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        else
            return ResponseEntity.ok(objectMapper.writeValueAsString(byPostTitle));
    }

    // display all without author id
    @GetMapping("/posts/withoutId")
    public ResponseEntity displayPostsWithoutId () throws JsonProcessingException {
        List<PostsProjections> allPosts = postRepository.findAllPosts();
       //List<Post> allPosts = postRepository.findAll();

        return ResponseEntity.ok(objectMapper.writeValueAsString(allPosts));
    }

    // edit posts Title by author
    @PutMapping("/posts/title/{id}")
    public ResponseEntity editPostTitle(@RequestBody String newTitle, @PathVariable("id") int idPost,
                                    @RequestHeader("userid") int userId) throws JsonProcessingException {
        Optional<Post> postById = postRepository.findByPostId(idPost);

        if (postById.get().getUserId() == userId){
            Post editedPost;
            editedPost = postById.get();
            editedPost.setPostTitle(newTitle);
            postRepository.save(editedPost);
            return ResponseEntity.ok(objectMapper.writeValueAsString(editedPost));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }   

    // edit postBody by author
    @PutMapping("/posts/body/{id}")
    public ResponseEntity editPostBody(@RequestBody String newBody, @PathVariable("id") int idPost,
                                    @RequestHeader("userid") int userId) throws JsonProcessingException {
        Optional<Post> postById = postRepository.findByPostId(idPost);

        if (postById.get().getUserId() == userId){
            Post editedPost;
            editedPost = postById.get();
            editedPost.setPostBody(newBody);
            postRepository.save(editedPost);
            return ResponseEntity.ok(objectMapper.writeValueAsString(editedPost));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // delete post by author
    @DeleteMapping("/posts/{id}")
    public ResponseEntity deletePost(@PathVariable("id") int idPost, @RequestHeader("userid") int userId){
        Optional<Post> postById = postRepository.findByPostId(idPost);
        if (postById.get().getUserId() == userId){
            postRepository.delete(postById.get());
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @GetMapping("/rest")
    public ResponseEntity update(){
        JSON json = new JSON();
        StringBuffer sb = json.updateDB();

        JSONArray posts = new JSONArray(sb.toString());

            for (int i = 0; i <posts.length(); i++ ) {
                JSONObject post = posts.getJSONObject(i);
                int userId = post.getInt("userId");
                int postId = post.getInt("id");
                String title = post.getString("title");
                String body = post.getString("body");
                Post newPost = new Post(userId, postId, title, body);
                postRepository.save(newPost);
            }

        return ResponseEntity.ok().build();
    }



}