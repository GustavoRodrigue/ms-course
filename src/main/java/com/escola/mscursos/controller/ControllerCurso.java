package com.escola.mscursos.controller;

import com.escola.mscursos.dto.DtoCurso;
import com.escola.mscursos.repository.entity.EntityCurso;
import com.escola.mscursos.service.ServiceCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/curso")
public class ControllerCurso {

    @Autowired
    private ServiceCurso serviceCurso;

    @PostMapping()
    public ResponseEntity<EntityCurso> criarCurso(@RequestBody DtoCurso curso){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(serviceCurso.criar(curso));

    }

    @GetMapping()
    public ResponseEntity<List<EntityCurso>> listatodos(){

        return ResponseEntity.ok(serviceCurso.listartodos());

    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityCurso> listarporid(@PathVariable UUID id){
        return ResponseEntity.ok(serviceCurso.listarporid(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityCurso> alterarcurso(@RequestBody DtoCurso curso, @PathVariable UUID id){
        return ResponseEntity.ok(serviceCurso.alterarcurso(curso, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        serviceCurso.deletar(id);
       return ResponseEntity.noContent().build();

    }
}
