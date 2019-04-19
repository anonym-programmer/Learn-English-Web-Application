package pl.robert.api.app.challenge.domain;

import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;

import java.time.LocalDateTime;

class ChallengeFactory {

    static Challenge create(CreateChallengeDto dto) {

        return Challenge.builder()
                .attacker(dto.getAttacker())
                .defender(dto.getDefender())
                .dateOfCreation(LocalDateTime.now())
                .status(ChallengeStatus.PENDING)
                .questions(dto.getQuestions())
                .build();
    }
}
