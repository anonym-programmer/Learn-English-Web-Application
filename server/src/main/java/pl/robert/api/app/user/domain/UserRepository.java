package pl.robert.api.app.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    User findByUsername(String username);

    User findByEmail(String email);

    @Query(value="SELECT username FROM users ORDER BY random() LIMIT 1", nativeQuery = true)
    String findRandomUsername();
}
