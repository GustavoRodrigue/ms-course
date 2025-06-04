package com.escola.mscursos.controller;

import com.escola.mscursos.dto.DtoCourse;
import com.escola.mscursos.repository.entity.EntityCourse;
import com.escola.mscursos.service.ServiceCourse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping ("/course")
public class ControllerCourse {

    @Autowired
    private ServiceCourse serviceCourse;

    @PostMapping()
    public ResponseEntity<EntityCourse> register(@RequestBody DtoCourse course){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(serviceCourse.register(course));

    }

    @GetMapping()
    public ResponseEntity<List<EntityCourse>> listAll(){

        return ResponseEntity.ok(serviceCourse.listAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityCourse> listById(@PathVariable UUID id){
        return ResponseEntity.ok(serviceCourse.listById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityCourse> changeCourse(@RequestBody DtoCourse course, @PathVariable UUID id){
        return ResponseEntity.ok(serviceCourse.changeCourse(course, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        serviceCourse.delete(id);
       return ResponseEntity.noContent().build();

    }
}
