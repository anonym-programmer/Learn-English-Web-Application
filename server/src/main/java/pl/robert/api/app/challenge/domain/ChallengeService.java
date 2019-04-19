package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;
import pl.robert.api.app.challenge.domain.dto.MakeChallengeDto;
import pl.robert.api.app.opponent.OpponentFacade;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.user.domain.UserFacade;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ChallengeService {

    ChallengeRepository repository;
    OpponentFacade opponentFacade;
    QuestionFacade questionFacade;
    UserFacade userFacade;

    void add(MakeChallengeDto dto) {
        repository.saveAndFlush(ChallengeFactory.create(
                new CreateChallengeDto(
                        opponentFacade.addAndReturnOpponent(
                                new CreateOpponentDto(
                                    transformAnswers(dto.getAnswers()),
                                    calculateCorrectAnswers(dto.getAnswers(), dto.getQuestionsIds()),
                                    userFacade.findUserByUsername(dto.getAttackerUsername())
                                )
                        ),
                        opponentFacade.addAndReturnOpponent(
                                new CreateOpponentDto(
                                    userFacade.findUserByUsername(dto.getDefenderUsername())
                                )
                        ),
                        questionFacade.getQuestionsByIds(dto.getQuestionsIds())
                )
        ));
    }

    private String transformAnswers(char[] answers) {
        return new String(answers).replaceAll(".(?!$)", "$0:");
    }

    private String calculateCorrectAnswers(char[] answers, long[] questionsId) {
        return transformAnswers(questionFacade.calculateCorrectAnswers(answers, questionsId));
    }

    void delete(long id) {
        repository.delete(repository.findById(id));
    }

    boolean isChallengeNotExists(long id) {
        return repository.findById(id) == null;
    }
}
