package pl.robert.api.app.opponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface OpponentRepository extends JpaRepository<Opponent, Long> {

    @Query("SELECT o.id FROM Opponent o WHERE o.user.id = :attackerId AND o.result = 'NONE' AND o.myAnswers IS NOT NULL")
    List<Long> findIdsOfAttackerPendingChallenges(@Param("attackerId") long attackerId);

    @Query("SELECT o.id FROM Opponent o WHERE o.user.id = :defenderId AND o.result = 'NONE' AND o.myAnswers IS NULL")
    List<Long> findIdsOfDefenderPendingChallenges(@Param("defenderId") long defenderId);

    @Query("SELECT o.id FROM Opponent o WHERE o.user.id = :userId AND o.result <> 'NONE'")
    List<Long> findIdsOfUserCompletedChallenges(@Param("userId") long userId);

    Opponent findFirstByOrderByIdDesc();
}
