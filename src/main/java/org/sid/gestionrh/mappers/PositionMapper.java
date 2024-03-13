package org.sid.gestionrh.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.entities.Position;
import org.sid.gestionrh.models.requests.DepartmentAddRequest;
import org.sid.gestionrh.models.requests.DepartmentRequest;
import org.sid.gestionrh.models.requests.PositionAddRequest;
import org.sid.gestionrh.models.requests.PositionRequest;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.sid.gestionrh.models.response.PositionResponse;

@Mapper(componentModel = "spring")
public interface PositionMapper extends ApplicationMapper<PositionRequest, PositionResponse, Position>{
    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);
    Position addRequestToEntity(PositionAddRequest positionAddRequest);
}
