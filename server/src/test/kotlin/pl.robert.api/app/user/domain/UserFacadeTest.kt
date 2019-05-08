package pl.robert.api.app.user.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import pl.robert.api.app.user.domain.dto.*

@RunWith(SpringRunner::class)
@SpringBootTest
class UserFacadeTest {

    @Autowired
    private lateinit var facade: UserFacade

    @Autowired
    private lateinit var repository: UserRepository

    @ParameterizedTest(name = "Correct objects in given array")
    @MethodSource("correctCreateUserDtos")
    fun `Shouldn't reject an errors on CreateUserDto obj`(dto: CreateUserDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertFalse(result.hasErrors())
    }

    @ParameterizedTest(name = "Incorrect objects in given array")
    @MethodSource("incorrectCreateUserDtos")
    fun `Should reject an errors on CreateUserDto obj`(dto: CreateUserDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "b@b.com",
        "microsoft@outlook.com",
        "mark.zuckerberg@facemail.com",
        "penguin@linux.com",
        "comma@mail.com",
        "james.java@mail.com",
        "rob@gmail.com",
        "iLoveApple@icloud.com",
        "freshAccount@gmail.com"
    ])
    fun `Shouldn't reject an error on invalid email of forgotten account`(email: String) {
        val dto = ForgotUserCredentialsDto(email)
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertFalse(result.hasErrors())
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "first@mail.com",
        "second@mail.com",
        "third@mail.com"
    ])
    fun `Should reject an error on invalid email of forgotten account`(email: String) {
        val dto = ForgotUserCredentialsDto(email)
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    @ParameterizedTest(name = "Correct objects in given array")
    @MethodSource("correctChangeUserPasswordDtos")
    fun `Shouldn't reject an errors on ChangeUserPasswordDto obj`(dto: ChangeUserPasswordDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertFalse(result.hasErrors())
    }

    @ParameterizedTest(name = "Incorrect objects in given array")
    @MethodSource("incorrectChangeUserPasswordDtos")
    fun `Should reject an errors on ChangeUserPasswordDto obj`(dto: ChangeUserPasswordDto) {
        val result = createBindingResult(dto)
        facade.checkInputData(dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    companion object {
        @JvmStatic
        fun correctCreateUserDtos() = listOf(
                Arguments {
                    arrayOf(
                            CreateUserDto(
                                    "Robert",
                                    "roberto@mail.com",
                                    "passw",
                                    "passw"
                            ),
                            CreateUserDto("John",
                                    "john.doe@email.com",
                                    "passw",
                                    "passw"
                            ),
                            CreateUserDto(
                                    "Tommy",
                                    "tomtomm@email.com",
                                    "passw",
                                    "passw"
                            )
                    )
                }
        )

        @JvmStatic
        fun incorrectCreateUserDtos() = listOf(
                Arguments {
                    arrayOf(
                            // username should be unique
                            CreateUserDto(
                                    "LinusTorvalds",
                                    "e@e.com",
                                    "passw",
                                    "passw"
                            ),

                            // email should be unique
                            CreateUserDto(
                                    "John",
                                    "penguin@linux.com",
                                    "passw",
                                    "passw"
                            ),

                            // passwords should be the same
                            CreateUserDto(
                                    "John",
                                    "email@email.com",
                                    "pass",
                                    "password"
                            )
                    )
                }
        )

        @JvmStatic
        fun correctChangeUserPasswordDtos() = listOf(
                Arguments {
                    arrayOf(
                            ChangeUserPasswordDto(
                                    "password123",
                                    "password123"
                            ),
                            ChangeUserPasswordDto(
                                    "password321",
                                    "password321"
                            )
                    )
                }
        )

        @JvmStatic
        fun incorrectChangeUserPasswordDtos() = listOf(
                Arguments {
                    arrayOf(
                            // password and confirmed password are not the same
                            ChangeUserPasswordDto(
                                    "password123",
                                    "321drowssap"
                            ),
                            ChangeUserPasswordDto(
                                    "password321",
                                    "123drowssap"
                            )
                    )
                }
        )

        @JvmStatic
        fun correctChangeUserEmailDtos() = listOf(
                Arguments {
                    arrayOf(
                            ChangeUserEmailDto(
                                    "email@email.com",
                                    "email@email.com"
                            ),
                            ChangeUserEmailDto(
                                    "joejo@email.com",
                                    "joejo@email.com"
                            )
                    )
                }
        )

        @JvmStatic
        fun incorrectChangeUserEmailDtos() = listOf(
                Arguments {
                    arrayOf(
                            // email should be unique
                            ChangeUserEmailDto(
                                    "penguin@linux.com",
                                    "penguin@linux.com"
                            ),

                            // email should have correct format
                            ChangeUserEmailDto(
                                    "wrongFormat",
                                    "wrongFormat"
                            ),

                            // email and confirmed email are not the same
                            ChangeUserEmailDto(
                                    "tom@mail.com",
                                    "jack@email.com"
                            )
                    )
                }
        )
    }

    private fun createBindingResult(obj: Any): BindingResult {
        return DataBinder(obj).bindingResult
    }

    @Test
    fun `Should check if user has an admin role`() {
        Assertions.assertFalse(facade.isInputDataCorrect(DeleteUserDto(1L)))
    }

    @ParameterizedTest
    @ValueSource(longs = [2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `Should check if users doesn't have admin role`(userId: Long) {
        Assertions.assertTrue(facade.isInputDataCorrect(DeleteUserDto(userId)))
    }

    @Test
    fun `Should get a user`() {
        // Given initialized dto
        val dto = CreateUserDto(
                "Mark",
                "mark@email.com",
                "passw",
                "passw"
        )

        // When we add user dto
        facade.add(dto)

        // Then system has this user
        Assertions.assertNotNull(repository.findByEmail(dto.email))
    }

    @Test
    fun `Should change user password and email`() {
        // Given user from db
        val user = facade.findUserByUsername("a")

        // Given variables with old and new password
        val oldPassword = user.password
        val newPassword = "newpass123"

        // Given variables with old and new email
        val oldEmail = user.email
        val newEmail = "newemail123@email.com"

        // When we change password
        facade.changePassword(user, newPassword)

        // And when we change email
        facade.changeEmail(user, newEmail)

        // Then user has new password and new email
        Assertions.assertNotEquals(oldPassword, facade.findUserByUsername("a").password)
        Assertions.assertNotEquals(oldEmail, facade.findUserByUsername("a").email)
    }

    @Test
    fun `Should add exprience to current user`() {
        // Given user from db
        val user = facade.findUserByUsername("Fresh")

        // When we add experience to this user
        facade.addExperience(user.username, 30)

        // Then user has this experience
        Assertions.assertEquals("30", facade.findUserByUsername(user.username).userDetails.expierience)
    }

    @Test
    fun `Should increment user number of wins, loses, draws, posts, comments, votes`() {
        // Given user from db
        val user = facade.findUserByUsername("Fresh")

        // When we increment wins
        facade.incrementUserWins(user.username)

        // Then user has incremented wins
        Assertions.assertEquals("1", facade.findUserByUsername(user.username).userDetails.numberOfWins)

        // And we increment loses
        facade.incrementUserLoses(user.username)

        // Then user has incremented loses
        Assertions.assertEquals("1", facade.findUserByUsername(user.username).userDetails.numberOfLoses)

        // And we increment draws
        facade.incrementUserDraws(user.username)

        // Then user has incremented draws
        Assertions.assertEquals("1", facade.findUserByUsername(user.username).userDetails.numberOfDraws)

        // And we increment posts
        facade.incrementUserPosts(user.username)

        // Then user has incremented posts
        Assertions.assertEquals("1", facade.findUserByUsername(user.username).userDetails.numberOfPosts)

        // And we increment comments
        facade.incrementUserComments(user.username)

        // Then user has incremented comments
        Assertions.assertEquals("1", facade.findUserByUsername(user.username).userDetails.numberOfComments)

        // And we increment votes
        facade.incrementUserVotes(user.username)

        // Then user has incremented votes
        Assertions.assertEquals("1", facade.findUserByUsername(user.username).userDetails.numberOfVotes)
    }

    @Test
    fun `Should get first 3 users`() {
        Assertions.assertEquals(3, facade.findAll(PageRequest.of(0, 3)).size)
    }

    @Test
    fun `Should query SignInDto obj by given username`() {
        Assertions.assertNotNull(facade.querySignInByUsername("SteveJobs"))
    }

    @Test
    fun `Should query UserOwnProfileQuery obj by given username`() {
        Assertions.assertNotNull(facade.queryUserOwnProfile("SteveJobs"))
    }

    @Test
    fun `Should query UserChallengeProfileQuery obj by given username`() {
        Assertions.assertNotNull(facade.queryUserChallengeProfile("SteveJobs"))
    }

    @Test
    fun `Should query UserForumProfileQuery obj by given username`() {
        Assertions.assertNotNull(facade.queryUserForumProfile("SteveJobs"))
    }

    @Test
    fun `Should query UserProfileQuery obj by given username`() {
        Assertions.assertNotNull(facade.queryUserProfile("SteveJobs"))
    }

    @Test
    @RepeatedTest(value = 20)
    fun `Should query random username for 20 times and attackerUsername cannot be output`() {
        Assertions.assertNotEquals("Steve Jobs", facade.queryRandomUsername("Steve Jobs"))
    }
}
