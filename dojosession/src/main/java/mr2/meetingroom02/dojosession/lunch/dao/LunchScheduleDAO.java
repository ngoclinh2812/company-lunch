package mr2.meetingroom02.dojosession.lunch.dao;

import mr2.meetingroom02.dojosession.base.dao.BaseDAO;
import mr2.meetingroom02.dojosession.base.exception.NotFoundException;
import mr2.meetingroom02.dojosession.employee.entity.Employee;
import mr2.meetingroom02.dojosession.lunch.dto.LunchScheduleResponseDTO;
import mr2.meetingroom02.dojosession.lunch.entity.*;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static mr2.meetingroom02.dojosession.base.exception.message.LunchScheduleExceptionMessage.LUNCH_SCHEDULE_NOT_FOUND;

@Stateless
public class LunchScheduleDAO extends BaseDAO<LunchSchedule> {

    @PersistenceContext
    private EntityManager entityManager;

    public LunchScheduleDAO() {
        super(LunchSchedule.class);
    }

    public LunchSchedule getScheduleLunch(Long scheduleId)  {
            TypedQuery<LunchSchedule> query = entityManager.createQuery("SELECT ls FROM LunchSchedule ls WHERE ls.id = :id", LunchSchedule.class)
                    .setParameter("id", scheduleId);
        return query.getSingleResult();
    }

    public LunchScheduleResponseDTO getScheduleLunchWithDTO(Long scheduleId) throws NoResultException {
        return entityManager.createQuery(
                "SELECT NEW mr2.meetingroom02.dojosession.lunch.dto.LunchScheduleResponseDTO(ls.id, ls.startDate, ls.endDate, m.date, me.name) " +
                        "FROM LunchSchedule ls " +
                        "LEFT JOIN FETCH ls.menuList m " +
                        "LEFT JOIN FETCH m.mealList me " +
                        "WHERE ls.id = :id",
                LunchScheduleResponseDTO.class
        ).setParameter("id", scheduleId).getSingleResult();
    }

    public List<LunchSchedule> findOverlapLunchSchedule(LocalDate startDate, LocalDate endDate) {
        try {
            TypedQuery<LunchSchedule> query = entityManager.createQuery(
                "SELECT ls FROM LunchSchedule ls " +
                        "WHERE :startDate between ls.startDate and ls.endDate " +
                        "OR :endDate between ls.startDate and ls.endDate"
                , LunchSchedule.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
            List<LunchSchedule> lunchSchedules = query.getResultList();
            return lunchSchedules;
        } catch (NoResultException e) {
            return null;
        }
    }

    public LunchOrder getLunchByOrderedByUser(Long employeeId, Long lunchScheduleId) {
        try {
            Query query = entityManager.createNamedQuery("getLunchOrderByEmployeeAndLunchScheduleId", LunchOrder.class)
                    .setParameter("employeeId", employeeId)
                    .setParameter("lunchScheduleId", lunchScheduleId);
            return (LunchOrder) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<LunchSchedule> getLunchScheduleUpcomingWeek() {
        TypedQuery<LunchSchedule> query = entityManager.createQuery(
                "SELECT ls FROM LunchSchedule ls " +
                        "JOIN Menu m ON m.schedule.id = ls.id " +
                        "JOIN MenuDish md ON md.menu.id = m.id " +
                        "JOIN Dish d ON d.id = md.dish.id " +
                        "WHERE ls.startDate >= TRUNC(CURRENT_DATE, 'week') + 1 " +
                        "ORDER BY ls.startDate ASC ", LunchSchedule.class
        );

        return query.getResultList();

    }
}
