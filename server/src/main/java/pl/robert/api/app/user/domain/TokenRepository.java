package pl.robert.api.app.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface TokenRepository extends JpaRepository<Token, Long> {

    Token findById(long id);

    Token findByConfirmationToken(String confirmationToken);

    Token findByUser(User user);
}
