package Azumi.org.Post;

import Azumi.org.Log.Logs;
import Azumi.org.Log.LogsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class PostService {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PostRepository postRepository;
    @Autowired
    LogsRepository logsRepository;

    // display all posts
    @GetMapping("/posts")
    public ResponseEntity displayPostsId() throws JsonProcessingException {
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
    public ResponseEntity displayPostsWithoutId() throws JsonProcessingException {
        List<PostsProjections> allPosts = postRepository.findAllPosts();
        return ResponseEntity.ok(objectMapper.writeValueAsString(allPosts));
    }

    // edit posts Title by author
    @PutMapping("/posts/title/{id}")
    public ResponseEntity editPostTitle(@RequestBody String newTitle, @PathVariable("id") int idPost,
                                        @RequestHeader("userid") int userId) throws JsonProcessingException {
        Optional<Post> postById = postRepository.findByPostId(idPost);

        if (postById.get().getUserId() == userId) {
            Post editedPost;
            editedPost = postById.get();
            editedPost.setPostTitle(newTitle);
            postRepository.save(editedPost);
            logsRepository.save(new Logs(editedPost.getUserId(), editedPost.getPostId(), "EDIT TITLE"));
            return ResponseEntity.ok(objectMapper.writeValueAsString(editedPost));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //edit postBody by author
    @PutMapping("/posts/body/{id}")
    public ResponseEntity editPostBody(@RequestBody String newBody, @PathVariable("id") int idPost,
                                       @RequestHeader("userid") int userId) throws JsonProcessingException {
        Optional<Post> postById = postRepository.findByPostId(idPost);

        if (postById.get().getUserId() == userId) {
            Post editedPost;
            editedPost = postById.get();
            editedPost.setPostBody(newBody);
            postRepository.save(editedPost);
            logsRepository.save(new Logs(editedPost.getUserId(), editedPost.getPostId(), "EDIT BODY"));
            return ResponseEntity.ok(objectMapper.writeValueAsString(editedPost));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // update DB
    @PutMapping("/rest")
    public ResponseEntity update() {
        period();
        return ResponseEntity.ok().build();
    }

    // delete post by author
    @DeleteMapping("/posts/{id}")
    public ResponseEntity deletePost(@PathVariable("id") int idPost, @RequestHeader("userid") int userId) {
        Optional<Post> postById = postRepository.findByPostId(idPost);
        if (postById.get().getUserId() == userId) {
            postRepository.delete(postById.get());
            logsRepository.save(new Logs(postById.get().getUserId(), postById.get().getPostId(), "DELETE POST"));
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }



    // automatic update DB at 20:00
    @Scheduled(cron = "0 0 20 * * *")
    public void period()    {
        DownloadFromURLApi json = new DownloadFromURLApi();
        StringBuffer sb = json.updateDB();
        JSONArray posts = new JSONArray(sb.toString());
        for (int i = 0; i < posts.length(); i++) {
            JSONObject post = posts.getJSONObject(i);
            int userId = post.getInt("userId");
            int postId = post.getInt("id");
            String title = post.getString("title");
            String body = post.getString("body");
            Post newPost = new Post(userId, postId, title, body);
            List<Logs> editTitle = logsRepository.findByIdAndActive(postId, "EDIT TITLE");
            List<Logs> editBody = logsRepository.findByIdAndActive(postId, "EDIT BODY");
            List<Logs> deletePost = logsRepository.findByIdAndActive(postId, "DELETE POST");
            Optional<Post> byPostId = postRepository.findByPostId(postId);

            if (deletePost.isEmpty()) {
                if (!editTitle.isEmpty()) {
                    newPost.setPostTitle(byPostId.get().getPostTitle());
                }
                if (!editBody.isEmpty()) {
                    newPost.setPostBody(byPostId.get().getPostBody());
                }
                postRepository.save(newPost);
            }
        }
    }
}