package pl.robert.api.app.opponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface OpponentRepository extends JpaRepository<Opponent, Long> {

    @Query("SELECT o.id FROM Opponent o WHERE o.user.id = :defenderId AND o.result = 'NONE' AND o.myAnswers IS NOT NULL")
    List<Long> findIdsOfAttackerPendingChallenges(@Param("defenderId") long defenderId);
}
