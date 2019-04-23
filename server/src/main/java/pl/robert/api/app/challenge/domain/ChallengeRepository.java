package pl.robert.api.app.challenge.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    Challenge findById(long id);

    Challenge findByAttackerId(long id);

    Challenge findByDefenderId(long id);
}
