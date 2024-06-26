package mr2.meetingroom02.dojosession.lunchSchedule.rest;

import mr2.meetingroom02.dojosession.auth.utility.JwtUtils;
import mr2.meetingroom02.dojosession.base.exception.BadRequestException;
import mr2.meetingroom02.dojosession.base.exception.DuplicateException;
import mr2.meetingroom02.dojosession.base.exception.NotFoundException;
import mr2.meetingroom02.dojosession.lunchSchedule.dto.*;
import mr2.meetingroom02.dojosession.menu.dto.MenuResponseDTO;
import mr2.meetingroom02.dojosession.lunchSchedule.service.LunchScheduleService;
import mr2.meetingroom02.dojosession.menu.service.MenuService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("lunch-schedule")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class LunchScheduleResource {

    @Inject
    private LunchScheduleService lunchScheduleService;

    @Inject
    private MenuService menuService;

    @Inject
    private JwtUtils jwtUtils;

    @GET
    @Path("/{id}")
    public Response getLunchScheduleById(@PathParam(value = "id") Long scheduleId) throws NotFoundException {
        LunchScheduleResponseDTO responseDTO = lunchScheduleService.getLunchScheduleById(scheduleId);
        return Response.ok().entity(responseDTO).build();
    }

    @GET
    @Path("/next-week")
    public Response getLunchScheduleUpcomingWeek() {
        LunchScheduleResponseDTO responseDTO = lunchScheduleService.getLunchScheduleUpcomingWeek();
        return Response.ok().entity(responseDTO).build();
    }


}
