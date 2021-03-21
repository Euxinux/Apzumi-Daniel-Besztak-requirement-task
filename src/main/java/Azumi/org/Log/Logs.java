package Azumi.org.Log;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "logs")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Logs {

    @Autowired
    public Logs(int id, @NonNull int userId, @NonNull int postId, @NonNull String active) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private int userId;

    @NonNull
    private int postId;

    @NonNull
    private String active;

}

