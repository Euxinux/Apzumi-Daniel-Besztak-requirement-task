package Azumi.org.Post;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @NonNull
    @Column(name = "id_user")
    private int userId;

    @Id
    @Column(name = "id")
    private int postId;

    @NonNull
    @Column(name = "title")
    private String postTitle;

    @NonNull
    @Column(name = "body")
    private  String postBody;
}
