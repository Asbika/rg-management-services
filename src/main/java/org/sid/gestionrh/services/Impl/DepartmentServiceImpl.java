package org.sid.gestionrh.services.Impl;

import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.entities.Employee;
import org.sid.gestionrh.exceptions.ResourceAlreadyExistsException;
import org.sid.gestionrh.exceptions.ResourceNotFound;
import org.sid.gestionrh.mappers.DepartmentMapper;
import org.sid.gestionrh.mappers.EmployeeMapper;
import org.sid.gestionrh.models.requests.*;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.sid.gestionrh.models.response.EmployeeResponse;
import org.sid.gestionrh.repositories.DepartmentRepository;
import org.sid.gestionrh.repositories.EmployeeRepository;
import org.sid.gestionrh.repositories.PositionRepository;
import org.sid.gestionrh.services.DepartmentService;
import org.sid.gestionrh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse add(DepartmentAddRequest request) {
        boolean nameAndLocationExist = departmentRepository.findByNameAndLocation(request.getName(),request.getLocation()).isPresent();
        if(!nameAndLocationExist){
            Department department = DepartmentMapper.INSTANCE.addRequestToEntity(request);
            departmentRepository.save(department);
            return DepartmentMapper.INSTANCE.entityToResponse(department);
        }else{
            String message = messageSource.getMessage("resource.department.alreadyExists.message",new Object[]{request.getName(),request.getLocation()},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceAlreadyExistsException(message);
        }
    }

    @Override
    public List<DepartmentResponse> get() {
        if(departmentRepository.findAll().isEmpty()){
            String message = messageSource.getMessage("department.notFound.message",null,"No Message", LocaleContextHolder.getLocale());
            throw new RuntimeException(message);
        }else {
            return DepartmentMapper.INSTANCE.listToResponseList(departmentRepository.findAll());
        }
    }

    @Override
    public DepartmentResponse get(Long id) {
        Optional<Department> departmentById = departmentRepository.findById(id);
        if(!departmentById.isPresent()){
            String message = messageSource.getMessage("resource.department.notFound.message",new Object[]{"Id",id},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else{
            DepartmentResponse departmentResponse = DepartmentMapper.INSTANCE.entityToResponse(departmentById.get());
            return departmentResponse;
        }
    }

    @Override
    public DepartmentResponse update(DepartmentUpdateRequest request, Long id) {
        Optional<Department> departmentById = departmentRepository.findById(id);
        if(!departmentById.isPresent()){
            String message = messageSource.getMessage("resource.department.notFound.message",new Object[]{"Id",id},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            Department department = departmentById.get();
            department.setName(request.getName());
            department.setLocation(request.getLocation());

            Department updatedDepartment = departmentRepository.save(department);
            DepartmentResponse departmentResponse  = DepartmentMapper.INSTANCE.entityToResponse(updatedDepartment);
            return departmentResponse;
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(!department.isPresent()){
            String message = messageSource.getMessage("resource.department.notFound.message",new Object[]{"Id",id},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            departmentRepository.deleteById(id);
        }
    }

    @Override
    public DepartmentResponse get(String location) {
        Optional<Department> departmentByLocation = departmentRepository.findByLocation(location);
        if(!departmentByLocation.isPresent()){
            String message = messageSource.getMessage("resource.department.location.notFound.message",new Object[]{location},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            return DepartmentMapper.INSTANCE.entityToResponse(departmentByLocation.get());
        }
    }

    @Override
    public DepartmentResponse getByName(String name) {
        Optional<Department> departmentByName = departmentRepository.findByName(name);
        if(!departmentByName.isPresent()){
            String message = messageSource.getMessage("resource.department.notFound.message",new Object[]{"name",name},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            return DepartmentMapper.INSTANCE.entityToResponse(departmentByName.get());
        }
    }
}
