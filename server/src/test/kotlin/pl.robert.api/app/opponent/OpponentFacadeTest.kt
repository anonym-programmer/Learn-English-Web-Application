package pl.robert.api.app.opponent

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.robert.api.app.opponent.dto.CreateOpponentDto
import pl.robert.api.app.user.domain.UserFacade

@RunWith(SpringRunner::class)
@SpringBootTest
class OpponentFacadeTest {

    @Autowired
    private lateinit var facade: OpponentFacade

    @Autowired
    private lateinit var repository: OpponentRepository

    @Autowired
    private lateinit var userFacade: UserFacade

    @Test
    fun `Should get an opponent`() {
        // Given initialized dto
        val dto = CreateOpponentDto(
                null,
                null,
                userFacade.findUserByUsername("SteveJobs")
        )

        // When we add opponent dto
        facade.add(dto)

        // Then system has this opponent
        Assertions.assertNotNull("SteveJobs", repository.findFirstByOrderByIdDesc().user.username)
    }

    @ParameterizedTest
    @CsvSource(
            "WIN, YOU WON",
            "LOSE, YOU LOST",
            "DRAW, YOU DREW"
    )
    fun `Should transform result`(result: String, expectedMessage: String) {
        Assertions.assertEquals(expectedMessage, facade.transformResult(result))
    }

    @ParameterizedTest
    @CsvSource(
            "1:1:1:1:1, 5",
            "1:1:1:1:0, 4",
            "1:1:1:0:0, 3",
            "1:1:0:0:0, 2",
            "1:0:0:0:0, 1",
            "0:0:0:0:0, 0",
            "0:0:0:1:0, 1",
            "0:1:0:1:0, 2",
            "0:1:1:1:0, 3",
            "1:1:0:1:1, 4"
    )
    fun `Should count correct answers`(answersStatus: String, score: Int) {
        Assertions.assertEquals(score, facade.countCorrectAnswers(answersStatus))
    }

    @ParameterizedTest
    @MethodSource("answersStatus")
    fun `Should transform answers status`(answersStatus: String, list: List<Char>) {
        Assertions.assertEquals(list, facade.transformAnswersStatus(answersStatus))
    }

    companion object {
        @JvmStatic
        fun answersStatus() = listOf(
                Arguments.of("1:1:1:1:1", listOf('1', '1', '1', '1', '1')),
                Arguments.of("1:1:1:1:0", listOf('1', '1', '1', '1', '0')),
                Arguments.of("1:1:1:0:0", listOf('1', '1', '1', '0', '0')),
                Arguments.of("1:1:0:0:0", listOf('1', '1', '0', '0', '0')),
                Arguments.of("1:0:0:0:0", listOf('1', '0', '0', '0', '0')),
                Arguments.of("0:0:0:0:0", listOf('0', '0', '0', '0', '0')),
                Arguments.of("0:0:0:1:0", listOf('0', '0', '0', '1', '0')),
                Arguments.of("0:1:0:1:0", listOf('0', '1', '0', '1', '0')),
                Arguments.of("0:1:1:1:0", listOf('0', '1', '1', '1', '0')),
                Arguments.of("1:1:0:1:1", listOf('1', '1', '0', '1', '1'))
        )
    }

    @Test
    fun `Should query ids of attacker pending challenges`() {
        Assertions.assertNotNull(facade.queryIdsOfAttackerPendingChallenges(5L))
    }

    @Test
    fun `Should query ids of defender pending challenges`() {
        Assertions.assertNotNull(facade.queryIdsOfDefenderPendingChallenges(5L))
    }

    @Test
    fun `Should query ids of user completed challenges`() {
        Assertions.assertNotNull(facade.queryIdsOfUserCompletedChallenges(5L))
    }
}
