package org.sid.gestionrh.controllers;

import jakarta.validation.Valid;
import org.sid.gestionrh.models.requests.EmployeeAddRequest;
import org.sid.gestionrh.models.requests.EmployeeRequest;
import org.sid.gestionrh.models.requests.EmployeeUpdateRequest;
import org.sid.gestionrh.models.response.EmployeeResponse;
import org.sid.gestionrh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private MessageSource messageSource;

    public EmployeeController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EmployeeResponse> add(@Valid @RequestBody EmployeeAddRequest employeeAddRequest){
        return new ResponseEntity<>(employeeService.add(employeeAddRequest), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_RH') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeResponse>> get(){
        System.out.println("controller "+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return new ResponseEntity<>(employeeService.get(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@RequestBody EmployeeUpdateRequest employeeUpdateRequest, @PathVariable Long id){
        return new ResponseEntity<>(employeeService.update(employeeUpdateRequest,id),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(employeeService.get(id),HttpStatus.OK);
    }

    @GetMapping("X")
    public ResponseEntity<EmployeeResponse> get(@PathVariable("email") String email){
        return ResponseEntity.ok(employeeService.get(email));
    }

    @GetMapping("/employee")
    public ResponseEntity<EmployeeResponse> get(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return ResponseEntity.ok(employeeService.get(firstName,lastName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        employeeService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("photo") MultipartFile photo, @PathVariable("id") Long id){
        employeeService.setPhoto(photo,id);
        String message = messageSource.getMessage("employee.photo.message",null,"No Message", LocaleContextHolder.getLocale());
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable("id") Long employeeyId){
        Resource photo = employeeService.getPhoto(employeeyId);
        // attachment; filename = hicham.png
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+photo.getFilename()+"\"").body(photo);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByDeppartment(@PathVariable("name") String name){
        return ResponseEntity.ok(employeeService.getByDepartment(name));
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeBypOSITION(@PathVariable("title") String title){
        return ResponseEntity.ok(employeeService.getByPsition(title));
    }
    @GetMapping("/date")
    public ResponseEntity<List<EmployeeResponse>> getByDate(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date1, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date2) {
        return ResponseEntity.ok(employeeService.getByDate(date1, date2));
    }
}
