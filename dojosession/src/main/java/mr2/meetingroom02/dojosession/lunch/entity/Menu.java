package mr2.meetingroom02.dojosession.lunch.entity;

import lombok.*;
import mr2.meetingroom02.dojosession.base.entity.BaseEntity;

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

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_schedule_id")
    private LunchSchedule lunchSchedule;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "menu_meal",
            joinColumns = { @JoinColumn(name = "menu_id")},
            inverseJoinColumns = { @JoinColumn(name = "meal_id") }
    )
    private List<Meal> mealList;

}