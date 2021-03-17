package Azumi.org.Log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogsRepository extends JpaRepository<Logs,Long> {

    @Query(value = "SELECT * FROM apzumi.logs WHERE post_id = ?1 AND active = ?2", nativeQuery = true)
    List<Logs> findByIdAndActive (int post_id, String active);
}
