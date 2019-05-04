package pl.robert.api.app.comment.domain

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.robert.api.app.comment.domain.CommentFacade

@RunWith(SpringRunner::class)
@SpringBootTest
class CommentFacadeTest {

    @Autowired
    private lateinit var facade: CommentFacade
}
