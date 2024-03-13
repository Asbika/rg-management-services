package org.sid.gestionrh.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.entities.Employee;
import org.sid.gestionrh.entities.Position;
import org.sid.gestionrh.models.requests.EmployeeAddRequest;
import org.sid.gestionrh.models.requests.EmployeeRequest;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.sid.gestionrh.models.response.EmployeeResponse;
import org.sid.gestionrh.models.response.PositionResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-13T19:32:44+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee requestToEntity(EmployeeRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.firstName( employeeRequest.getFirstName() );
        employee.lastName( employeeRequest.getLastName() );
        employee.email( employeeRequest.getEmail() );

        return employee.build();
    }

    @Override
    public EmployeeResponse entityToResponse(Employee employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        EmployeeResponse.EmployeeResponseBuilder employeeResponse = EmployeeResponse.builder();

        employeeResponse.id( employeeEntity.getId() );
        employeeResponse.firstName( employeeEntity.getFirstName() );
        employeeResponse.lastName( employeeEntity.getLastName() );
        employeeResponse.email( employeeEntity.getEmail() );
        employeeResponse.employeeDate( employeeEntity.getEmployeeDate() );
        employeeResponse.position( positionToPositionResponse( employeeEntity.getPosition() ) );
        employeeResponse.department( departmentToDepartmentResponse( employeeEntity.getDepartment() ) );

        return employeeResponse.build();
    }

    @Override
    public List<EmployeeResponse> listToResponseList(List<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeResponse> list = new ArrayList<EmployeeResponse>( employees.size() );
        for ( Employee employee : employees ) {
            list.add( entityToResponse( employee ) );
        }

        return list;
    }

    @Override
    public Employee addRequestToEntity(EmployeeAddRequest companyEntityAddRequest) {
        if ( companyEntityAddRequest == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.firstName( companyEntityAddRequest.getFirstName() );
        employee.lastName( companyEntityAddRequest.getLastName() );
        employee.email( companyEntityAddRequest.getEmail() );

        return employee.build();
    }

    protected PositionResponse positionToPositionResponse(Position position) {
        if ( position == null ) {
            return null;
        }

        PositionResponse.PositionResponseBuilder positionResponse = PositionResponse.builder();

        positionResponse.id( position.getId() );
        positionResponse.title( position.getTitle() );
        positionResponse.description( position.getDescription() );

        return positionResponse.build();
    }

    protected DepartmentResponse departmentToDepartmentResponse(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentResponse.DepartmentResponseBuilder departmentResponse = DepartmentResponse.builder();

        departmentResponse.id( department.getId() );
        departmentResponse.name( department.getName() );
        departmentResponse.location( department.getLocation() );

        return departmentResponse.build();
    }
}
