package org.sid.gestionrh.controllers;

import jakarta.validation.Valid;
import org.sid.gestionrh.models.requests.DepartmentAddRequest;
import org.sid.gestionrh.models.requests.DepartmentUpdateRequest;
import org.sid.gestionrh.models.requests.EmployeeAddRequest;
import org.sid.gestionrh.models.requests.EmployeeUpdateRequest;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.sid.gestionrh.models.response.EmployeeResponse;
import org.sid.gestionrh.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    DepartmentService departmentService;

    public DepartmentController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> add(@Valid @RequestBody DepartmentAddRequest departmentAddRequest){
        return new ResponseEntity<>(departmentService.add(departmentAddRequest), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> get(){
        return new ResponseEntity<>(departmentService.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(@RequestBody DepartmentUpdateRequest departmentUpdateRequest, @PathVariable Long id){
        return new ResponseEntity<>(departmentService.update(departmentUpdateRequest,id),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(departmentService.get(id),HttpStatus.OK);
    }
    @GetMapping("/location/{location}")
    public ResponseEntity<DepartmentResponse> get(@PathVariable("location") String location){
        return ResponseEntity.ok(departmentService.get(location));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentResponse> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok(departmentService.getByName(name));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        departmentService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
