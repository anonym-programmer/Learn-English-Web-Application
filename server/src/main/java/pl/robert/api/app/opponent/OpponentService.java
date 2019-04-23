package pl.robert.api.app.opponent;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class OpponentService {

    OpponentRepository repository;

    Opponent addOpponent(CreateOpponentDto dto) {
        return repository.save(OpponentFactory.create(dto));
    }

    List<Long> findIdsOfAttackerPendingChallenges(long attackerId) {
        return repository.findIdsOfAttackerPendingChallenges(attackerId);
    }

    List<Long> findIdsOfDefenderPendingChallenges(long defenderId) {
        return repository.findIdsOfDefenderPendingChallenges(defenderId);
    }
}
