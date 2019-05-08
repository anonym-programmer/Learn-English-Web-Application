package pl.robert.api.app.vote.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.post.domain.Post;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.vote.domain.dto.CreateVoteDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VoteFacade {

    VoteValidator validator;
    VoteService service;
    PostFacade postFacade;
    UserFacade userFacade;

    public void checkInputData(CreateVoteDto dto, BindingResult result) {
        if (!result.hasErrors()) validator.checkInputData(dto, result);
    }

    public void add(CreateVoteDto dto) {
        Post post = postFacade.findPostById(Long.parseLong(dto.getPostId()));
        postFacade.updatePostVote(post, dto.getType().toUpperCase().charAt(0));
        userFacade.incrementUserVotes(dto.getUsername());
        service.saveAndFlush(VoteFactory.create(
                dto,
                post,
                userFacade.findUserByUsername(dto.getUsername()))
        );
    }
}
