package mr2.meetingroom02.dojosession.menu.dto;

import lombok.*;
import mr2.meetingroom02.dojosession.menuDish.dto.MenuDishResponseDTO;

import javax.json.bind.annotation.JsonbDateFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuRequestDTO implements Serializable {

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate date;

    private List<MenuDishResponseDTO> meals;

}
