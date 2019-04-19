package pl.robert.api.app.vote.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.post.domain.Post;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.user.domain.User;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.vote.domain.dto.CreateVoteDto;

import static pl.robert.api.app.shared.Constants.*;

@Slf4j
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

        if (userFacade.isUsernameNotExists(dto.getUsername())) {
            result.rejectValue(F_USERNAME, C_NOT_EXISTS, M_USERNAME_NOT_EXISTS);
        }

        if (!voteService.isTypeCorrect(dto.getType())) {
            result.rejectValue(F_TYPE, C_NOT_MATCH, M_TYPE_NOT_MATCH);
        }

        if (!result.hasErrors()) {

            Post post = postFacade.findPostById(Long.parseLong(dto.getPostId()));
            User user = userFacade.findUserByUsername(dto.getUsername());

            if (voteService.hasUserAlreadyVoted(post, user)) {

                log.info("User already voted");

                if (voteService.isOldTypeMatchNewType(post, user, dto.getType())) {
                    result.rejectValue(F_TYPE, C_EXISTS, M_TYPE_EXISTS);
                    log.warn("User cannot add the same type of vote");
                } else {
                    postFacade.swapTypeOfVote(dto.getType(), post);
                    voteService.delete(voteService.findByPostAndUser(post, user));
                    log.info("User swap old-vote to the new-vote, and the old-vote has been deleted");
                }
            }
        }
    }
}
