package org.sid.gestionrh.controllers;

import jakarta.validation.Valid;
import org.sid.gestionrh.models.requests.DepartmentAddRequest;
import org.sid.gestionrh.models.requests.DepartmentUpdateRequest;
import org.sid.gestionrh.models.requests.PositionAddRequest;
import org.sid.gestionrh.models.requests.PositionUpdateRequest;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.sid.gestionrh.models.response.PositionResponse;
import org.sid.gestionrh.repositories.PositionRepository;
import org.sid.gestionrh.services.DepartmentService;
import org.sid.gestionrh.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
public class PositionController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    PositionService positionService;

    public PositionController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @PostMapping
    public ResponseEntity<PositionResponse> add(@Valid @RequestBody PositionAddRequest positionAddRequest){
        return new ResponseEntity<>(positionService.add(positionAddRequest), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PositionResponse>> get(){
        return new ResponseEntity<>(positionService.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PositionResponse> update(@RequestBody PositionUpdateRequest positionUpdateRequest, @PathVariable Long id){
        System.out.println("request");
        return new ResponseEntity<>(positionService.update(positionUpdateRequest,id),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PositionResponse> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(positionService.get(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        positionService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<PositionResponse> getByName(@PathVariable("title") String title){
        return ResponseEntity.ok(positionService.get(title));
    }
}
