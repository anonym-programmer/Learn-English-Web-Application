package pl.robert.api.app.vote.domain

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
import pl.robert.api.app.vote.domain.dto.CreateVoteDto

@RunWith(SpringRunner::class)
@SpringBootTest
class VoteFacadeTest {

    @Autowired
    private lateinit var facade: VoteFacade

    @Autowired
    private lateinit var repository: VoteRepository

    @ParameterizedTest(name = "Correct objects in given array")
    @MethodSource("correct")
    fun `Should validate dto objects without errors`(dto: CreateVoteDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertFalse(result.hasErrors())
    }

    @ParameterizedTest(name = "Incorrect objects in given array")
    @MethodSource("incorrect")
    fun `Should validate dto objects with errors`(dto: CreateVoteDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    companion object {
        @JvmStatic
        fun correct() = listOf(
                Arguments {
                    arrayOf(
                            CreateVoteDto("1", "LinusTorvalds", "YES"),
                            CreateVoteDto("2", "LinusTorvalds", "YES"),
                            CreateVoteDto("3", "LinusTorvalds", "NO")
                    )
                }
        )

        @JvmStatic
        fun incorrect() = listOf(
                Arguments {
                    arrayOf(
                            CreateVoteDto("1000", "LinusTorvalds", "YES"),
                            CreateVoteDto("2", "UnknownUser", "YES"),
                            CreateVoteDto("3", "LinusTorvalds", "ABC")
                    )
                }
        )
    }

    @Test
    fun `Should reject errors on invalid post id, username and type of vote`() {
        // Given initialized dto with wrong post id, username and type of vote
        val dto = CreateVoteDto("1001", "UnknownUsername", "PROBABLY")
        val result = createBindingResult(dto)

        // When we check dto
        facade.checkInputData(dto, result)

        // Then result has 3 errors
        Assertions.assertTrue(result.hasFieldErrors("postId")
                .and(result.hasFieldErrors("username"))
                .and(result.hasFieldErrors("type"))
        )
    }

    @Test
    fun `Should reject error on the same type of vote`() {
        // Given initailized dto
        val dto = CreateVoteDto("5", "LinusTorvalds", "YES")
        val result = createBindingResult(dto)

        // When we check dto
        facade.checkInputData(dto, result)

        // And save
        facade.add(dto)

        // Then result has no error
        Assertions.assertFalse(result.hasErrors())

        // When we check again the same object
        facade.checkInputData(dto, result)

        // Then result should have error, because user cannot vote 2 times on the same type
        Assertions.assertTrue(result.hasErrors())
    }

    @Test
    fun `Should swap type of vote without error`() {
        // Given initailized dto
        val dto = CreateVoteDto("5", "LinusTorvalds", "YES")
        val result = createBindingResult(dto)

        // When we save
        facade.add(dto)

        // Then result has no error
        Assertions.assertFalse(result.hasErrors())

        // When we change type of vote from YES to NO
        dto.type = "NO"

        // And we save
        facade.add(dto)

        // Then result still has no error
        Assertions.assertFalse(result.hasErrors())
    }

    private fun createBindingResult(obj: Any): BindingResult {
        return DataBinder(obj).bindingResult
    }

    @Test
    fun `Should get a vote`() {
        //  Given initialized dto
        val dto = CreateVoteDto("1", "LinusTorvalds", "YES")

        // When we add vote dto
        facade.add(dto)

        // Then system has this vote
        Assertions.assertNotNull(repository.findById(dto.postId.toLong()).isPresent)
    }
}
