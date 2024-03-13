package org.sid.gestionrh.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.sid.gestionrh.entities.Position;
import org.sid.gestionrh.models.requests.PositionAddRequest;
import org.sid.gestionrh.models.requests.PositionRequest;
import org.sid.gestionrh.models.response.PositionResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-13T19:32:43+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class PositionMapperImpl implements PositionMapper {

    @Override
    public Position requestToEntity(PositionRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        position.title( employeeRequest.getTitle() );
        position.description( employeeRequest.getDescription() );

        return position.build();
    }

    @Override
    public PositionResponse entityToResponse(Position employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        PositionResponse.PositionResponseBuilder positionResponse = PositionResponse.builder();

        positionResponse.id( employeeEntity.getId() );
        positionResponse.title( employeeEntity.getTitle() );
        positionResponse.description( employeeEntity.getDescription() );

        return positionResponse.build();
    }

    @Override
    public List<PositionResponse> listToResponseList(List<Position> employees) {
        if ( employees == null ) {
            return null;
        }

        List<PositionResponse> list = new ArrayList<PositionResponse>( employees.size() );
        for ( Position position : employees ) {
            list.add( entityToResponse( position ) );
        }

        return list;
    }

    @Override
    public Position addRequestToEntity(PositionAddRequest positionAddRequest) {
        if ( positionAddRequest == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        position.title( positionAddRequest.getTitle() );
        position.description( positionAddRequest.getDescription() );

        return position.build();
    }
}
