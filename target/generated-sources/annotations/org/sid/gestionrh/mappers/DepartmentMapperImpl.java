package org.sid.gestionrh.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.models.requests.DepartmentAddRequest;
import org.sid.gestionrh.models.requests.DepartmentRequest;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-13T19:32:44+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department requestToEntity(DepartmentRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        department.location( employeeRequest.getLocation() );

        return department.build();
    }

    @Override
    public DepartmentResponse entityToResponse(Department employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        DepartmentResponse.DepartmentResponseBuilder departmentResponse = DepartmentResponse.builder();

        departmentResponse.id( employeeEntity.getId() );
        departmentResponse.name( employeeEntity.getName() );
        departmentResponse.location( employeeEntity.getLocation() );

        return departmentResponse.build();
    }

    @Override
    public List<DepartmentResponse> listToResponseList(List<Department> employees) {
        if ( employees == null ) {
            return null;
        }

        List<DepartmentResponse> list = new ArrayList<DepartmentResponse>( employees.size() );
        for ( Department department : employees ) {
            list.add( entityToResponse( department ) );
        }

        return list;
    }

    @Override
    public Department addRequestToEntity(DepartmentAddRequest departmentAddRequest) {
        if ( departmentAddRequest == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        department.name( departmentAddRequest.getName() );
        department.location( departmentAddRequest.getLocation() );

        return department.build();
    }
}
