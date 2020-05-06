package pl.robert.api.app.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface UserRepository extends Repository<User, Long> {

    void save(User user);

    void delete(User user);

    Optional<User> findById(long id);

    User findByUsername(String username);

    User findByEmail(String email);

    @Query(value = "SELECT username FROM users ORDER BY random() LIMIT 1", nativeQuery = true)
    String findRandomUsername();

    int count();

    Page<User> findAll(Pageable pageable);
}
