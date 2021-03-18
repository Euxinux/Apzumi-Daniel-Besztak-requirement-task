package Azumi.org.Log;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "logs")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Logs {

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

