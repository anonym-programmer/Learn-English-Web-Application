package pl.robert.api.app.vote.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.vote.domain.dto.CreateVoteDto;

import static pl.robert.api.app.shared.Constants.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class VoteValidator {

    VoteService voteService;
    PostFacade postFacade;
    UserFacade userFacade;

    void checkInputData(CreateVoteDto dto, BindingResult result) {

        if (!postFacade.isPostExists(Long.parseLong(dto.getPostId()))) {
            result.rejectValue(F_POST_ID, C_NOT_EXISTS, M_POST_NOT_EXISTS);
        }

        if (!userFacade.isUserExists(dto.getUsername())) {
            result.rejectValue(F_USERNAME, C_NOT_EXISTS, M_USERNAME_NOT_EXISTS);
        }

        if (!voteService.isTypeCorrect(dto.getType())) {
            result.rejectValue(F_TYPE, C_NOT_MATCH, M_TYPE_NOT_MATCH);
        }
    }
}
