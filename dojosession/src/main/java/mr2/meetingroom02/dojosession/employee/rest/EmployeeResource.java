package mr2.meetingroom02.dojosession.employee.rest;

import mr2.meetingroom02.dojosession.base.exception.BadRequestException;
import mr2.meetingroom02.dojosession.base.exception.DuplicateException;
import mr2.meetingroom02.dojosession.base.exception.NotFoundException;
import mr2.meetingroom02.dojosession.employee.dto.EmployeeCreateRequestDTO;
import mr2.meetingroom02.dojosession.employee.dto.EmployeeResponseDTO;
import mr2.meetingroom02.dojosession.employee.dto.EmployeeUpdateRequestDTO;
import mr2.meetingroom02.dojosession.employee.service.EmployeeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("employees")
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @GET
    public Response getEmployeesByCategory(@QueryParam("gender") String gender,
                                           @QueryParam("dept_id") Long departmentId,
                                           @QueryParam("page_number") Long pageNumber,
                                           @QueryParam("page_size") Long pageSize) throws NotFoundException {
        List<EmployeeResponseDTO> employeeList = employeeService.getEmployeesByCategory(gender, departmentId, pageNumber, pageSize);
        return Response.ok().entity(employeeList).build();
    }

    @POST
    public Response addNewEmployee(@Valid EmployeeCreateRequestDTO employeeCreateRequestDTO) throws BadRequestException, NotFoundException, DuplicateException {
        EmployeeResponseDTO createdEmployee = employeeService.add(employeeCreateRequestDTO);
        return Response.created(URI.create("employees/" + createdEmployee.getId())).entity(createdEmployee).build();
    }

    @PUT
    @Path("employee/{id}")
    public Response updateEmployee(@Valid EmployeeUpdateRequestDTO dto,
                                   @PathParam("id") Long employeeId)
            throws BadRequestException, DuplicateException {
        EmployeeResponseDTO responseDTO = employeeService.update(employeeId, dto);
        return Response.ok().entity(responseDTO).build();
    }

    @DELETE
    @Path("/{employeeId}")
    public Response deleteEmployee(@PathParam("employeeId") Long employeeId) throws NotFoundException {
        employeeService.remove(employeeId);
        return Response.noContent().build();
    }

}
