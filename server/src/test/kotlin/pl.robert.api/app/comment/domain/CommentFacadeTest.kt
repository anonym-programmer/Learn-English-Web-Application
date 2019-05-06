package pl.robert.api.app.comment.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import pl.robert.api.app.comment.domain.dto.CreateCommentDto

@RunWith(SpringRunner::class)
@SpringBootTest
class CommentFacadeTest {

    @Autowired
    private lateinit var facade: CommentFacade

    @Autowired
    private lateinit var repository: CommentRepository

    @ParameterizedTest
    @MethodSource("incorrectDtoObjects")
    fun `Should validate dto objects with errors`(dto: CreateCommentDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    @ParameterizedTest
    @MethodSource("correctDtoObjects")
    fun `Should validate dto objects without errors`(dto: CreateCommentDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertFalse(result.hasErrors())
    }

    private fun createBindingResult(obj: Any): BindingResult {
        return DataBinder(obj).bindingResult
    }

    companion object {
        @JvmStatic
        fun correctDtoObjects() = listOf(
                Arguments.of(CreateCommentDto("1", "LinusTorvalds", "This is a simple comment")),
                Arguments.of(CreateCommentDto("5", "MarkZuckerberg", "This is a simple comment")),
                Arguments.of(CreateCommentDto("3", "JamesGosling", "This is a simple comment"))
        )

        @JvmStatic
        fun incorrectDtoObjects() = listOf(
                Arguments.of(CreateCommentDto("1001", "LinusTorvalds", "This is a simple comment")),
                Arguments.of(CreateCommentDto("1", "UnknownUser", "This is a simple comment")),
                Arguments.of(CreateCommentDto("1001", "UnknownUser", "This is a simple comment"))
        )
    }

    @Test
    fun `Should get a comment`() {
        //  Given initialized dto
        val dto = CreateCommentDto("1", "LinusTorvalds", "This is a simple comment")

        // When we add comment dto
        facade.add(dto)

        // Then system has this comment
        Assertions.assertNotNull(repository.findById(dto.postId.toLong()).isPresent)
    }

    @Test
    fun `Should query all comments by post`() {
        Assertions.assertNotNull(facade.queryCommentsByPost(1))
    }
}
