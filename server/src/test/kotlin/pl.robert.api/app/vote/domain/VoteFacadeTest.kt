package pl.robert.api.app.vote.domain

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class VoteFacadeTest {

    @Autowired
    private lateinit var facade: VoteFacade
}
