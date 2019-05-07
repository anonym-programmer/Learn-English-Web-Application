package pl.robert.api.app.user.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import pl.robert.api.app.user.domain.dto.*

@RunWith(SpringRunner::class)
@SpringBootTest
class UserFacadeTest {

    @Autowired
    private lateinit var facade: UserFacade

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
        "a@a.com",
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
                            CreateUserDto("Robert", "roberto@mail.com", "passw", "passw"),
                            CreateUserDto("John", "john.doe@email.com", "passw", "passw"),
                            CreateUserDto("Tommy", "tomtomm@email.com", "passw", "passw")
                    )
                }
        )

        @JvmStatic
        fun incorrectCreateUserDtos() = listOf(
                Arguments {
                    arrayOf(
                            // username should be unique
                            CreateUserDto("LinusTorvalds", "e@e.com", "passw", "passw"),

                            // email should be unique
                            CreateUserDto("John", "penguin@linux.com", "passw", "passw"),

                            // passwords should be the same
                            CreateUserDto("John", "email@email.com", "pass", "password")
                    )
                }
        )

        @JvmStatic
        fun correctChangeUserPasswordDtos() = listOf(
                Arguments {
                    arrayOf(
                            ChangeUserPasswordDto("password123", "password123"),
                            ChangeUserPasswordDto("password321", "password321")
                    )
                }
        )

        @JvmStatic
        fun incorrectChangeUserPasswordDtos() = listOf(
                Arguments {
                    arrayOf(
                            // password and confirmed password are not the same
                            ChangeUserPasswordDto("password123", "321drowssap"),
                            ChangeUserPasswordDto("password321", "123drowssap")
                    )
                }
        )

        @JvmStatic
        fun correctChangeUserEmailDtos() = listOf(
                Arguments {
                    arrayOf(
                            ChangeUserEmailDto("email@email.com", "email@email.com"),
                            ChangeUserEmailDto("joejo@email.com", "joejo@email.com")
                    )
                }
        )

        @JvmStatic
        fun incorrectChangeUserEmailDtos() = listOf(
                Arguments {
                    arrayOf(
                            // email should be unique
                            ChangeUserEmailDto("penguin@linux.com", "penguin@linux.com"),

                            // email should have correct format
                            ChangeUserEmailDto("wrongFormat", "wrongFormat"),

                            // email and confirmed email are not the same
                            ChangeUserEmailDto("tom@mail.com", "jack@email.com")
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
}
