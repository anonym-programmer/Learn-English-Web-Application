package pl.robert.api.app.post.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner
import pl.robert.api.app.post.domain.dto.CreatePostDto

@RunWith(SpringRunner::class)
@SpringBootTest
class PostFacadeTest {

    @Autowired
    private lateinit var facade: PostFacade

    @Autowired
    private lateinit var repository: PostRepository

    @Test
    fun `Should get first 3 posts`() {
        Assertions.assertEquals(3, facade.findAll(PageRequest.of(0, 3)).size)
    }

    @Test
    fun `Should find post by given id`() {
        Assertions.assertNotNull(facade.findPostById(1L))
    }

    @Test
    fun `Should up vote current post and swap to down vote`() {
        // When we get actual quantity of up votes
        var actualUpVotes = facade.findPostById(1L).upVote

        // And we up vote this post
        facade.updatePostVote(facade.findPostById(1L), 'Y')

        // Then actual quantity of up votes is increased by 1
        Assertions.assertEquals(++actualUpVotes, facade.findPostById(1L).upVote)

        // And we swap type of vote from YES to NO
        facade.swapTypeOfVote("NO", facade.findPostById(1L))

        // Then actual quantity of up votes is decreased by 1
        Assertions.assertEquals(--actualUpVotes, facade.findPostById(1L).upVote)
    }

    @Test
    fun `Should get a post`() {
        // Given initialized dto
        val dto = CreatePostDto(
                "This is a simple title",
                "This is a simple description"
        )

        // When we add post dto
        facade.add(dto, "LinusTorvalds")

        // And we check if system has this post
        Assertions.assertNotNull(facade.isPostExists(repository.findFirstByOrderByIdDesc().id))

        // Then we query this post
        // And check equal of titles
        Assertions.assertEquals(dto.title, facade.queryPost(repository.findFirstByOrderByIdDesc().id).title)
    }
}
