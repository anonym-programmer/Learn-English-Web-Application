package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitChallengeDto;
import pl.robert.api.app.challenge.query.ChallengePendingQuery;
import pl.robert.api.app.opponent.OpponentFacade;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.user.domain.UserFacade;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ChallengeService {

    ChallengeRepository repository;
    OpponentFacade opponentFacade;
    QuestionFacade questionFacade;
    UserFacade userFacade;

    void add(SubmitChallengeDto dto) {
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

    private String calculateCorrectAnswers(char[] answers, List<Long> questionsId) {
        return transformAnswers(questionFacade.calculateCorrectAnswers(answers, questionsId));
    }

    void delete(long id) {
        repository.delete(repository.findById(id));
    }

    boolean isChallengeNotExists(Long id) {
        return repository.findById(id).isEmpty();
    }

    List<ChallengePendingQuery> queryAttackerPendingChallenges(String username) {
        return List.copyOf(opponentFacade.findIdsOfAttackerPendingChallenges(userFacade.findUserByUsername(username).getId())
                .stream()
                .map(repository::findByAttackerId)
                .collect(Collectors.toList())
                .stream()
                .map(challenge -> new ChallengePendingQuery(
                        challenge.getDefender().getUser().getUsername(),
                        String.valueOf(challenge.getDateOfCreation()).substring(0, 10),
                        String.valueOf(challenge.getDateOfCreation()).substring(11, 19)))
                .collect(Collectors.toList()));
    }

    List<ChallengePendingQuery> queryDefenderPendingChallenges(String username) {
        return List.copyOf(opponentFacade.findIdsOfDefenderPendingChallenges(userFacade.findUserByUsername(username).getId())
                .stream()
                .map(repository::findByDefenderId)
                .collect(Collectors.toList())
                .stream()
                .map(challenge -> new ChallengePendingQuery(
                        challenge.getAttacker().getUser().getUsername(),
                        String.valueOf(challenge.getDateOfCreation()).substring(0, 10),
                        String.valueOf(challenge.getDateOfCreation()).substring(11, 19)))
                .collect(Collectors.toList()));
    }
}
