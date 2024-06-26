package mr2.meetingroom02.dojosession.menu.entity;

import lombok.*;
import mr2.meetingroom02.dojosession.base.entity.BaseEntity;
import mr2.meetingroom02.dojosession.lunchSchedule.entity.LunchSchedule;
import mr2.meetingroom02.dojosession.menuDish.entity.MenuDish;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Menu extends BaseEntity {

    @Column(name = "menu_date", unique = true)
    private LocalDate menuDate;

    @OneToMany(mappedBy = "menu")
    private List<MenuDish> menuDish;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private LunchSchedule lunchSchedule;

}
