package pl.robert.api.app.challenge.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    Challenge findById(long id);

    Challenge findByAttackerId(long id);

    Challenge findByDefenderId(long id);

    @Query("SELECT c FROM Challenge c WHERE c.attacker.id = :id OR c.defender.id = :id")
    Challenge findByDefenderOrAttackerId(@Param("id") long id);
}
