package pl.robert.api.app.question.domain

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class QuestionFacadeTest {

    @Autowired
    private lateinit var facade: QuestionFacade
}
