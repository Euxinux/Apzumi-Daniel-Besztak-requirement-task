package Azumi.org.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByPostTitle(String postTitle);

    @Query(value = "SELECT id, title, body FROM apzumi.posts", nativeQuery = true)
    List<PostsProjections> findAllPosts();

    Optional<Post> findByPostId(int id);

}
