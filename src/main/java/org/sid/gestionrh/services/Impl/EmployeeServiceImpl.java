package org.sid.gestionrh.services.Impl;

import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.entities.Employee;
import org.sid.gestionrh.entities.Position;
import org.sid.gestionrh.exceptions.ResourceAlreadyExistsException;
import org.sid.gestionrh.exceptions.ResourceNotFound;
import org.sid.gestionrh.mappers.EmployeeMapper;
import org.sid.gestionrh.models.requests.EmployeeAddRequest;
import org.sid.gestionrh.models.requests.EmployeeUpdateRequest;
import org.sid.gestionrh.models.response.EmployeeResponse;
import org.sid.gestionrh.repositories.DepartmentRepository;
import org.sid.gestionrh.repositories.EmployeeRepository;
import org.sid.gestionrh.repositories.PositionRepository;
import org.sid.gestionrh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Path uploadPath = Paths.get("uploads");
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

@Override
public EmployeeResponse add(EmployeeAddRequest employeeAddRequest) {
    boolean nameAndLastNameExist = employeeRepository.findByFirstNameAndLastName(
            employeeAddRequest.getFirstName(),
            employeeAddRequest.getLastName()
    ).isPresent();

    boolean emailExists = employeeRepository.findByEmail(employeeAddRequest.getEmail()).isPresent();

    Optional<Position> position = positionRepository.findById(employeeAddRequest.getPositionId());
    Optional<Department> department = departmentRepository.findById(employeeAddRequest.getDepartmentId());

    if (nameAndLastNameExist || emailExists) {
        if (nameAndLastNameExist) {
        String message = messageSource.getMessage("resource.alreadyExists.message",new Object[]{"Employe",employeeAddRequest.getFirstName(),employeeAddRequest.getLastName()},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceAlreadyExistsException(message);

        } else {
            String message = messageSource.getMessage("employee.email.message",new Object[]{employeeAddRequest.getEmail()},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceAlreadyExistsException(message);
        }
    }

    validatePositionAndDepartment(position, department);

    Employee employee = EmployeeMapper.INSTANCE.addRequestToEntity(employeeAddRequest);
    position.ifPresent(employee::setPosition);
    department.ifPresent(employee::setDepartment);

    employeeRepository.save(employee);

    return EmployeeMapper.INSTANCE.entityToResponse(employee);
    }

    private void validatePositionAndDepartment(Optional<Position> position, Optional<Department> department) {
        if (position.isEmpty() || department.isEmpty()) {
            String message = messageSource.getMessage("positionDepartment.notFound.message",null,"No Message", LocaleContextHolder.getLocale());
            throw new RuntimeException(message);
        }
    }
    @Override
    public List<EmployeeResponse> get() {
        if(employeeRepository.findAll().isEmpty()){
            String message = messageSource.getMessage("employees.notFound.message",null,"No Message", LocaleContextHolder.getLocale());
            throw new RuntimeException(message);
        }else {
            return EmployeeMapper.INSTANCE.listToResponseList(employeeRepository.findAll());
        }
    }
    @Override
    public EmployeeResponse get(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            String message = messageSource.getMessage("resource.notFound.message",new Object[]{"Id",id},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            EmployeeResponse employeeResponse = EmployeeMapper.INSTANCE.entityToResponse(employee.get());
            return employeeResponse;
        }
    }
    @Override
    public EmployeeResponse update(EmployeeUpdateRequest employeeUpdateRequest, Long id) {
        Optional<Employee> employeeById = employeeRepository.findById(id);
        if (employeeById.isEmpty()) {
            String message = messageSource.getMessage("resource.notFound.message",new Object[]{"Id",id},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }

        Employee employee = employeeById.get();
        employee.setFirstName(employeeUpdateRequest.getFirstName());
        employee.setLastName(employeeUpdateRequest.getLastName());
        employee.setEmail(employeeUpdateRequest.getEmail());

        positionRepository.findById(employeeUpdateRequest.getPositionId())
                .ifPresent(employee::setPosition);

        departmentRepository.findById(employeeUpdateRequest.getDepartmentId())
                .ifPresent(employee::setDepartment);

        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeResponse employeeResponse = EmployeeMapper.INSTANCE.entityToResponse(updatedEmployee);
        return employeeResponse;
    }

    @Override
    public void delete(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            String message = messageSource.getMessage("resource.notFound.message",new Object[]{"Id",id},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            employeeRepository.deleteById(id);
        }
    }
    @Override
    public EmployeeResponse get(String email) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if(!employee.isPresent()){
            String message = messageSource.getMessage("resource.notFound.message",new Object[]{"e-mail",email},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else {
            EmployeeResponse employeeResponse = EmployeeMapper.INSTANCE.entityToResponse(employee.get());
            return employeeResponse;
        }
    }
    @Override
    public EmployeeResponse get(String firstName, String lastName) {
        Optional<Employee> employee = employeeRepository.findByFirstNameAndLastName(firstName,lastName);
        if(!employee.isPresent()){
            String message = messageSource.getMessage("employee.firstName.lastName.notFound.message",new Object[]{firstName,lastName},"No Message", LocaleContextHolder.getLocale());
            throw new RuntimeException(message);
        }else{
            EmployeeResponse employeeResponse = EmployeeMapper.INSTANCE.entityToResponse(employee.get());
            return employeeResponse;
        }
    }

    @Override
    public Resource getPhoto(Long employeeId) {
        Optional<Employee> employeeById = employeeRepository.findById(employeeId);
        if (employeeById.isPresent()) {
            Path resolve = this.uploadPath.resolve(employeeById.get().getPhoto());
            try {
                UrlResource urlResource = new UrlResource(resolve.toUri());
                return urlResource;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }else{
            String message = messageSource.getMessage("resource.notFound.message",new Object[]{"Id",employeeId},"No Message", LocaleContextHolder.getLocale());
            throw new RuntimeException(message);
        }
    }

    @Override
    public List<EmployeeResponse> getByDepartment(String departmentName) {
        Optional<Department> department = departmentRepository.findByName(departmentName);
        if(department.isPresent()){
            return EmployeeMapper.INSTANCE.listToResponseList(employeeRepository.findByDepartment(department.get().getId()));
        }else{

            String message = messageSource.getMessage("resource.department.notFound.message",new Object[]{"name",departmentName},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }
    }

    @Override
    public List<EmployeeResponse> getByPsition(String positionTitle) {
        Optional<Position> position = positionRepository.findByTitle(positionTitle);
        if(position.isPresent()){
            return EmployeeMapper.INSTANCE.listToResponseList(employeeRepository.findByPosition(position.get().getId()));
        }else{
            String message = messageSource.getMessage("resource.position.notFound.message",new Object[]{"title",positionTitle},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }
    }

    @Override
    public List<EmployeeResponse> getByDate(Date date1, Date date2) {
        Optional<List<Employee>> employees = employeeRepository.findByDate(date1,date2);
        if(!employees.get().isEmpty()){
            return EmployeeMapper.INSTANCE.listToResponseList(employees.get());
        }else{
            String message = messageSource.getMessage("employees.notFound.message",null,"No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }
    }
    @Override
    public void setPhoto(MultipartFile photo, Long employeeId) {
        try {
            Path targetPath = this.uploadPath.resolve(photo.getOriginalFilename());
            Files.copy(photo.getInputStream(), targetPath);
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            if(employee.isPresent()){
                employee.get().setPhoto(photo.getOriginalFilename());
                employeeRepository.save(employee.get());
            }else{
                String message = messageSource.getMessage("resource.notFound.message",new Object[]{"Id",employeeId},"No Message", LocaleContextHolder.getLocale());
                throw new RuntimeException(message);
            }
        } catch (IOException e) {
            String message = messageSource.getMessage("upload.photo.message",null,"No Message", LocaleContextHolder.getLocale());
            throw new RuntimeException(message+e.getLocalizedMessage());
        }
    }
    @Override
    public boolean init() {
        boolean exists = Files.exists(uploadPath);
        if (!exists) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                String message = messageSource.getMessage("employee.photo.crate.folder.message",null,"No Message", LocaleContextHolder.getLocale());
                throw new RuntimeException(message, e);
            }
        }
        return exists;
    }
}
