package mr2.meetingroom02.dojosession.lunchSchedule.rest;

import mr2.meetingroom02.dojosession.base.exception.BadRequestException;
import mr2.meetingroom02.dojosession.base.exception.DuplicateException;
import mr2.meetingroom02.dojosession.base.exception.NotFoundException;
import mr2.meetingroom02.dojosession.lunchSchedule.dto.CreateLunchScheduleDTO;
import mr2.meetingroom02.dojosession.lunchSchedule.dto.CreateMenuRequestDTO;
import mr2.meetingroom02.dojosession.lunchSchedule.dto.LunchScheduleResponseDTO;
import mr2.meetingroom02.dojosession.lunchSchedule.service.LunchScheduleService;
import mr2.meetingroom02.dojosession.menu.dto.MenuResponseDTO;
import mr2.meetingroom02.dojosession.menu.service.MenuService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/back-office/lunch-schedule")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class BOLunchScheduleResource {

    @Inject
    private LunchScheduleService lunchScheduleService;

    @Inject
    private MenuService menuService;

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

    @POST
    public Response createLunchSchedule(
            @Valid CreateLunchScheduleDTO createLunchScheduleDTO) throws BadRequestException {
        LunchScheduleResponseDTO responseDTO = lunchScheduleService.createLunchSchedule(createLunchScheduleDTO);
        return Response.created(URI.create("lunch/" + responseDTO.getId())).entity(responseDTO).build();
    }

    @POST
    @Path("/{lunchScheduleId}/menu")
    public Response createLunchScheduleMenu(
            @Valid CreateMenuRequestDTO createMenuRequestDTO,
            @NotNull @PathParam("lunchScheduleId") Long lunchId
    ) throws DuplicateException, BadRequestException, NotFoundException {
        MenuResponseDTO responseDTO = menuService.createMenu(createMenuRequestDTO, lunchId);
        return Response.created(URI.create("lunch/" + responseDTO.getId())).entity(responseDTO).build();
    }
}
