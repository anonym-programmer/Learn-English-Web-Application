package pl.robert.api.app.admin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.user.domain.dto.DeleteUserDto;

@RestController
@RequestMapping("api/admin")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class AdminController {

    UserFacade userFacade;

    @DeleteMapping("user/{id}")
    public HttpEntity<?> deleteUserById(@PathVariable String id) {
        if (!userFacade.isInputDataCorrect(new DeleteUserDto(Long.parseLong(id)))) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        userFacade.deleteUserById(Long.parseLong(id));
        return ResponseEntity
                .ok()
                .build();
    }
}
