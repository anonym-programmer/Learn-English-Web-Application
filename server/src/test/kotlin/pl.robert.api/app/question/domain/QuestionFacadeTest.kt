package pl.robert.api.app.question.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class QuestionFacadeTest {

    @Autowired
    private lateinit var facade: QuestionFacade

    @Test
    @RepeatedTest(value = 10)
    fun `Should query 5 random Questions without duplicates for 10 times`() {
        Assertions.assertEquals(5, HashSet(facade.queryRandomQuestions()).size)
    }

    @Test
    fun `Should query 5 questions by given ids`() {
        Assertions.assertEquals(5, facade.queryQuestionsByIds(listOf(1, 2, 3, 4, 5)).size)
    }

    @ParameterizedTest
    @MethodSource("correctAnswersWithIds")
    @DisplayName("All answers should be correct")
    fun shouldCalculateAllAnswersCorrect(answers: List<Char>, ids: List<Long>) {
        Assertions.assertEquals(listOf('1', '1', '1', '1', '1'), facade.calculateCorrectAnswers(answers, ids))
    }

    @ParameterizedTest
    @MethodSource("incorrectAnswersWithIds")
    @DisplayName("All answers should be incorrect")
    fun shouldCalculateAllAnswersIncorrect(answers: List<Char>, ids: List<Long>) {
        Assertions.assertEquals(listOf('0', '0', '0', '0', '0'), facade.calculateCorrectAnswers(answers, ids))
    }

    companion object {
        @JvmStatic
        fun correctAnswersWithIds() = listOf(
                Arguments.of(listOf('b', 'a', 'd', 'b', 'd'), listOf<Long>(1, 2, 3, 4, 5)),
                Arguments.of(listOf('c', 'a', 'c', 'd', 'a'), listOf<Long>(10, 20, 30, 40, 50)),
                Arguments.of(listOf('d', 'c', 'b', 'a', 'a'), listOf<Long>(100, 51, 101, 27, 103)),
                Arguments.of(listOf('b', 'c', 'c', 'a', 'c'), listOf<Long>(4, 8, 15, 23, 42))
        )

        @JvmStatic
        fun incorrectAnswersWithIds() = listOf(
                Arguments.of(listOf('a', 'b', 'c', 'a', 'c'), listOf<Long>(1, 2, 3, 4, 5)),
                Arguments.of(listOf('a', 'c', 'd', 'c', 'b'), listOf<Long>(10, 20, 30, 40, 50)),
                Arguments.of(listOf('c', 'd', 'a', 'b', 'b'), listOf<Long>(100, 51, 101, 27, 103)),
                Arguments.of(listOf('c', 'b', 'b', 'd', 'b'), listOf<Long>(4, 8, 15, 23, 42))
        )
    }
}
