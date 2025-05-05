package com.escola.mscursos.service;

import com.escola.mscursos.dto.DtoCurso;
import com.escola.mscursos.dto.DtoErrorResponse;
import com.escola.mscursos.repository.RepositoryCurso;
import com.escola.mscursos.repository.entity.EntityCurso;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ServiceCurso {

    @Autowired
    private RepositoryCurso repositoryCurso;

    public EntityCurso criar(DtoCurso dtoCurso){
        log.info("ServiceCurso - criar - Dados recebidos: {}",dtoCurso);
       try {
        EntityCurso curso = new EntityCurso();

        curso.setId(UUID.randomUUID());
        curso.setNome(dtoCurso.getNome());
        curso.setDescricao(dtoCurso.getDescricao());
        curso.setCapacidade(dtoCurso.getCapacidade());


        return repositoryCurso.save(curso);
       } catch (Exception e) {
           throw new DtoErrorResponse(e.getMessage());
       }
    }

    public List<EntityCurso> listartodos(){
        log.info("ServiceCurso - listar todos - Dados recebidos ");
       try {
           return repositoryCurso.findAll();
       }catch (Exception e){
           throw new DtoErrorResponse(e.getMessage());
       }
    }

    public EntityCurso listarporid(UUID id){
        log.info("ServiceCurso - listar por id - Dados recebidos: {}", id);
     try {

         Optional<EntityCurso> curso = repositoryCurso.findById(id);

         if (curso.isPresent()) {
             return curso.get();
         } else {
             return null;
         }
     } catch (Exception e) {
         throw new DtoErrorResponse(e.getMessage());
     }
    }

    public EntityCurso alterarcurso(DtoCurso dtoCurso, UUID id){
        log.info("ServiceCurso - alterar curso - Dados recebidos: {}, {}", dtoCurso, id);
       try{
        EntityCurso curso =listarporid(id) ;
        if(curso !=null){
            curso.setNome(dtoCurso.getNome());
            curso.setDescricao(dtoCurso.getDescricao());
            curso.setCapacidade(dtoCurso.getCapacidade());

            return repositoryCurso.save(curso);
        } else{
           return null;
        }
       } catch (Exception e) {
           throw new DtoErrorResponse(e.getMessage());
       }

    }
    public void deletar(UUID id){
        log.info("ServiceCurso - deletar curso - Dados recebidos: {}",id);
       try{
        repositoryCurso.deleteById(id);
       } catch (Exception e) {
           throw new DtoErrorResponse(e.getMessage());
       }
    }




}
