package pl.robert.api.app.opponent;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.user.domain.UserFacade;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class OpponentService {

    OpponentRepository repository;
    QuestionFacade questionFacade;
    UserFacade userFacade;

    void addOpponents(CreateChallengeDto dto) {
        calculateCorrectAnswers(dto.getAnswers(), dto.getQuestionsIds());

        CreateOpponentDto attacker = new CreateOpponentDto();
        attacker.setMyAnswers(transformAnswers(dto.getAnswers()));
        attacker.setAnswerStatus(calculateCorrectAnswers(dto.getAnswers(), dto.getQuestionsIds()));
        attacker.setUser(userFacade.findUserByUsername(dto.getAttackerUsername()));
        repository.saveAndFlush(OpponentFactory.create(attacker));

        CreateOpponentDto defender = new CreateOpponentDto();
        defender.setUser(userFacade.findUserByUsername(dto.getDefenderUsername()));
        repository.saveAndFlush(OpponentFactory.create(defender));
    }

    private String transformAnswers(char[] answers) {
        return new String(answers).replaceAll(".(?!$)", "$0:");
    }

    private String calculateCorrectAnswers(char[] answers, long[] questionsId) {
        return transformAnswers(questionFacade.calculateCorrectAnswers(answers, questionsId));
    }
}
