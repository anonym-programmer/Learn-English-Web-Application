package pl.robert.api.app.post.domain

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.robert.api.app.post.domain.PostFacade

@RunWith(SpringRunner::class)
@SpringBootTest
class PostFacadeTest {

    @Autowired
    private lateinit var facade: PostFacade
}