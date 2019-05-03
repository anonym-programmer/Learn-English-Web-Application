package pl.robert.api.app.opponent;

import pl.robert.api.app.opponent.dto.CreateOpponentDto;

class OpponentFactory {

    static Opponent create(CreateOpponentDto dto) {
        return Opponent.builder()
                .myAnswers(dto.getMyAnswers())
                .answersStatus(dto.getAnswerStatus())
                .gainedExperienceForCorrectAnswers(null)
                .bonusExperienceForResult(null)
                .totalGainedExperience(null)
                .result(OpponentResult.NONE)
                .user(dto.getUser())
                .build();
    }
}
