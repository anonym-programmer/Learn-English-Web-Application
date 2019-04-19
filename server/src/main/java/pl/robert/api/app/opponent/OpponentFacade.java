package pl.robert.api.app.opponent;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OpponentFacade {

    OpponentService service;

    public Opponent addAndReturnOpponent(CreateOpponentDto dto) {
        return service.addOpponent(dto);
    }
}
