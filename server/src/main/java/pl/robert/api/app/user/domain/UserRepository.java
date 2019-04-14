package pl.robert.api.app.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    User findByUsername(String username);

    User findByEmail(String email);
}
