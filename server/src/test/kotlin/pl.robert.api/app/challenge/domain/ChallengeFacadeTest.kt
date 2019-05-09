package pl.robert.api.app.challenge.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import pl.robert.api.app.challenge.domain.dto.DeleteChallengeDto
import pl.robert.api.app.challenge.domain.dto.MakeChallengeDto
import pl.robert.api.app.challenge.domain.dto.SubmitChallengeDto
import pl.robert.api.app.challenge.domain.dto.SubmitPendingChallengeDto

@RunWith(SpringRunner::class)
@SpringBootTest
class ChallengeFacadeTest {

    @Autowired
    private lateinit var facade: ChallengeFacade

    @Autowired
    private lateinit var repository: ChallengeRepository

    @ParameterizedTest
    @CsvSource(
            "a, b",
            "a, BillGates",
            "a, MarkZuckerberg",
            "a, LinusTorvalds",
            "a, GeorgeHotz",
            "a, JamesGosling",
            "a, Rob",
            "a, SteveJobs",
            "a, Fresh"
    )
    fun `Shouldn't reject an errors on attacker and defender username`
            (attackerUsername: String, defenderUsername: String) {
        val dto = MakeChallengeDto(attackerUsername, defenderUsername)
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertFalse(result.hasErrors())
    }

    @ParameterizedTest
    @CsvSource(
            "a, a",
            "BillGates, BillGates",
            "SteveJobs, SteveJobs",
            "LinusTorvalds, LinusTorvalds"
    )
    fun `Should reject an error when attacker and defender username are equal`
            (attackerUsername: String, defenderUsername: String) {
        val dto = MakeChallengeDto(attackerUsername, defenderUsername)
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    @ParameterizedTest
    @CsvSource(
            "abc, cba",
            "Rob, abc",
            "Joe, John",
            "Tim, LinusTorvalds"
    )
    fun `Should reject an errors when attacker or defender username doesn't exists`
            (attackerUsername: String, defenderUsername: String) {
        val dto = MakeChallengeDto(attackerUsername, defenderUsername)
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    @ParameterizedTest(name = "Correct objects in given array")
    @MethodSource("correctSubmitChallengeDtos")
    fun `Shouldn't reject an error on SubmitChallengeDto`(dto: SubmitChallengeDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertFalse(result.hasErrors())
    }

    @ParameterizedTest(name = "Incorrect objects in given array")
    @MethodSource("incorrectSubmitChallengeDtos")
    fun `Should reject an error on SubmitChallengeDto`(dto: SubmitChallengeDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    companion object {
        @JvmStatic
        fun correctSubmitChallengeDtos() = listOf(
                Arguments {
                    arrayOf(
                            SubmitChallengeDto(
                                    "LinusTorvalds",
                                    "MarkZuckerberg",
                                    listOf(1, 2, 3, 4, 5),
                                    listOf('a', 'b', 'c', 'd', 'a')
                            ),
                            SubmitChallengeDto(
                                    "MarkZuckerberg",
                                    "LinusTorvalds",
                                    listOf(6, 7, 8, 9, 10),
                                    listOf('d', 'c', 'b', 'a', 'd')
                            ),
                            SubmitChallengeDto(
                                    "JamesGosling",
                                    "SteveJobs",
                                    listOf(101, 56, 76, 34, 18),
                                    listOf('c', 'b', 'a', 'd', 'b')
                            )
                    )
                }
        )

        @JvmStatic
        fun incorrectSubmitChallengeDtos() = listOf(
                Arguments {
                    arrayOf(
                            SubmitChallengeDto(
                                    "LinusTorvalds",
                                    "MarkZuckerberg",
                                    listOf(null, null, null, null, null),
                                    listOf(null, null, null, null, null)
                            ),
                            SubmitChallengeDto(
                                    "MarkZuckerberg",
                                    "LinusTorvalds",
                                    listOf(null, 1, 2, 3, 4),
                                    listOf('d', 'c', null, 'a', null)
                            ),
                            SubmitChallengeDto(
                                    "JamesGosling",
                                    "SteveJobs",
                                    listOf(1, 2, 3, 4, 5),
                                    listOf(null, 'a', 'b', 'c', 'd')
                            )
                    )
                }
        )
    }

    private fun createBindingResult(obj: Any): BindingResult {
        return DataBinder(obj).bindingResult
    }

    @Test
    fun `Should submit new challenge`() {
        // When we get actual size of submited challenges of attacker user
        val actualAttackerSubmitedChallengesSize =
                facade.queryAttackerPendingChallenges("LinusTorvalds").size

        // And we submit a challenge
        facade.submitChallenge(SubmitChallengeDto(
                "LinusTorvalds",
                "GeorgeHotz",
                listOf(90, 43, 34, 12, 100),
                listOf('a', 'b', 'c', 'a', 'd')
        ))

        // Then actual size + 1 from before is equal to actual size from now
        Assertions.assertEquals(actualAttackerSubmitedChallengesSize + 1,
                facade.queryAttackerPendingChallenges("LinusTorvalds").size)
    }

    @Test
    fun `Should submit pending challenge`() {
        // When we get actual size of submited pending challenges of attacker user
        val actualAttackerCompletedChallengesSize =
                facade.queryCompletedChallenges("LinusTorvalds").size

        // And we submit pending challenge
        facade.submitPendingChallenge(SubmitPendingChallengeDto(
                "6",
                "LinusTorvalds",
                listOf(9, 7, 5, 3, 1),
                listOf('a', 'b', 'c', 'a', 'd')
        ))

        // Then actual size + 1 from before is equal to actual size from now
        Assertions.assertEquals(actualAttackerCompletedChallengesSize + 1,
                facade.queryCompletedChallenges("LinusTorvalds").size)
    }

    @ParameterizedTest
    @CsvSource(
        "7, LinusTorvalds",
        "8, Rob",
        "9, SteveJobs"
    )
    fun `Should return true by validating DeleteChallengeDto`(challengeId: Long, defenderUsername: String) {
        Assertions.assertTrue(facade.isInputDataCorrect(DeleteChallengeDto(challengeId, defenderUsername)))
    }

    @ParameterizedTest
    @CsvSource(
        "1000, LinusTorvalds",
        "1000, MarkZuckerberg",
        "10, UnknownUser"
    )
    fun `Should return false by validating DeleteChallengeDto`(challengeId: Long, defenderUsername: String) {
        Assertions.assertFalse(facade.isInputDataCorrect(DeleteChallengeDto(challengeId, defenderUsername)))
    }

    @ParameterizedTest
    @ValueSource(longs = [1, 2, 3])
    fun `Should delete pending challenges`(challengeId: Long) {
        // When we delete pending challenge by id
        facade.delete(challengeId)

        // Then system doesn't have this challenge
        Assertions.assertNull(repository.findById(challengeId))
    }

    @Test
    fun `Should query attacker pending challenges`() {
        Assertions.assertNotNull(facade.queryAttackerPendingChallenges("LinusTorvalds"))
    }

    @Test
    fun `Should query defender pending challenges`() {
        Assertions.assertNotNull(facade.queryAttackerPendingChallenges("LinusTorvalds"))
    }

    @Test
    fun `Should query completed challenges of given user`() {
        Assertions.assertNotNull(facade.queryAttackerPendingChallenges("LinusTorvalds"))
    }

    @Test
    fun `Should query questions of defender by challenge id`() {
        Assertions.assertNotNull(facade.queryQuestionsOfDefenderChallengeId("7"))
    }

    @Test
    fun `Should query completed challenge details by challenge id`() {
        Assertions.assertNotNull(facade.queryCompletedChallengeDetailsByChallengeId(
                "5",
                "LinusTorvalds")
        )
    }

    @Test
    fun `Should query completed challenge details correct answers by challenge id`() {
        Assertions.assertNotNull(facade.queryCompletedChallengeDetailsCorrectAnswersByChallengeId(
                "5",
                "LinusTorvalds")
        )
    }
}
