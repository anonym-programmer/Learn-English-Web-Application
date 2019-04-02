package pl.robert.api.app.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    UserDetails findUserDetailsById(long id);
}
