package Azumi.org.Post;
import lombok.*;
import javax.persistence.*;
@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Post {
    @NonNull
    @Column(name = "id_user")
    private int userId;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int postId;

    @NonNull
    @Column(name = "title")
    private String postTitle;

    @NonNull
    @Column(name = "body")
    private  String postBody;
}
