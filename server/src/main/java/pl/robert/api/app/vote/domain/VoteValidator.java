package pl.robert.api.app.vote.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.vote.domain.dto.CreateVoteDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class VoteValidator {

    VoteService voteService;

    void checkInputData(CreateVoteDto dto, BindingResult result) {

    }
}
