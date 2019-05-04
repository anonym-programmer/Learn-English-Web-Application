import org.junit.runner.RunWith
import org.junit.runners.Suite
import pl.robert.api.app.challenge.domain.ChallengeFacadeTest
import pl.robert.api.app.comment.domain.CommentFacadeTest
import pl.robert.api.app.opponent.OpponentFacadeTest
import pl.robert.api.app.post.domain.PostFacadeTest
import pl.robert.api.app.question.domain.QuestionFacadeTest
import pl.robert.api.app.user.domain.UserFacadeTest
import pl.robert.api.app.vote.domain.VoteFacadeTest

@RunWith(Suite::class)
@Suite.SuiteClasses(value = [
    UserFacadeTest::class,
    PostFacadeTest::class,
    CommentFacadeTest::class,
    VoteFacadeTest::class,
    ChallengeFacadeTest::class,
    QuestionFacadeTest::class,
    OpponentFacadeTest::class
])
class FeatureTestSuite
