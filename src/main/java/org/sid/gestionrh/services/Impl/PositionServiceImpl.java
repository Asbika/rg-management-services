package org.sid.gestionrh.services.Impl;

import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.entities.Position;
import org.sid.gestionrh.exceptions.ResourceAlreadyExistsException;
import org.sid.gestionrh.exceptions.ResourceNotFound;
import org.sid.gestionrh.mappers.DepartmentMapper;
import org.sid.gestionrh.mappers.PositionMapper;
import org.sid.gestionrh.models.requests.*;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.sid.gestionrh.models.response.EmployeeResponse;
import org.sid.gestionrh.models.response.PositionResponse;
import org.sid.gestionrh.repositories.DepartmentRepository;
import org.sid.gestionrh.repositories.EmployeeRepository;
import org.sid.gestionrh.repositories.PositionRepository;
import org.sid.gestionrh.services.EmployeeService;
import org.sid.gestionrh.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public PositionResponse add(PositionAddRequest request) {
            boolean titleAndLocationExist = positionRepository.findByTitleAndDescription(request.getTitle(),request.getDescription()).isPresent();
            if(!titleAndLocationExist){
                Position position = PositionMapper.INSTANCE.addRequestToEntity(request);
                positionRepository.save(position);
                return PositionMapper.INSTANCE.entityToResponse(position);
            }else{
                String message = messageSource.getMessage("resource.position.alreadyExists.message",new Object[]{request.getTitle(),request.getDescription()},"No Message", LocaleContextHolder.getLocale());
                throw new ResourceAlreadyExistsException(message);
            }
    }

    @Override
    public List<PositionResponse> get() {
        if(positionRepository.findAll().isEmpty()){
            String message = messageSource.getMessage("position.notFound.message",null,"No Message", LocaleContextHolder.getLocale());
            throw new RuntimeException(message);
        }else {
            return PositionMapper.INSTANCE.listToResponseList(positionRepository.findAll());
        }
    }

    @Override
    public PositionResponse get(Long id) {
        Optional<Position> positionById = positionRepository.findById(id);
        if(!positionById.isPresent()){
            String message = messageSource.getMessage("resource.position.notFound.message",new Object[]{"Id",id},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else{
            PositionResponse positionResponse = PositionMapper.INSTANCE.entityToResponse(positionById.get());
            return positionResponse;
        }
    }

    @Override
    public PositionResponse update(PositionUpdateRequest request, Long id) {

        Optional<Position> positionById = positionRepository.findById(id);

        if(!positionById.isPresent()){
            String message = messageSource.getMessage("resource.position.notFound.message",new Object[]{"Id",id},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            Position position = positionById.get();
            position.setTitle(request.getTitle());
            position.setDescription(request.getDescription());

            Position updatedPosition = positionRepository.save(position);
            PositionResponse positionResponse  = PositionMapper.INSTANCE.entityToResponse(updatedPosition);
            return positionResponse;
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Position> position = positionRepository.findById(id);
        if(!position.isPresent()){
            String message = messageSource.getMessage("resource.position.notFound.message",new Object[]{"Id",id},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            positionRepository.deleteById(id);
        }
    }

    @Override
    public PositionResponse get(String title) {
        Optional<Position> positionByTitle = positionRepository.findByTitle(title);
        if(!positionByTitle.isPresent()){
            String message = messageSource.getMessage("resource.position.title.notFound.message",new Object[]{title},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            return PositionMapper.INSTANCE.entityToResponse(positionByTitle.get());
        }
    }
}
