package pl.robert.api.app.admin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.user.query.UserQuery;

@RestController
@RequestMapping("api/admin-query")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class AdminQueryController {

    UserFacade facade;

    @GetMapping("users")
    public Page<UserQuery> findAllUsers(Pageable pageable) {
        return facade.findAll(pageable);
    }
}
