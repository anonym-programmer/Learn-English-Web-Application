package pl.robert.api.app.user.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteUserDto {

    Long id;
}
