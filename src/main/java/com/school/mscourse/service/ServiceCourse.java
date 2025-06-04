package com.school.mscourse.service;

import com.school.mscourse.dto.DtoCourse;
import com.school.mscourse.dto.DtoErrorResponse;
import com.school.mscourse.repository.RepositoryCourse;
import com.school.mscourse.repository.entity.EntityCourse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class ServiceCourse {

    @Autowired
    private RepositoryCourse repositoryCourse;

    public EntityCourse register(DtoCourse dtoCourse){
        log.info("ServiceCurso - criar - Dados recebidos: {}",dtoCourse);
       try {
           EntityCourse course = new EntityCourse();

           course.setId(UUID.randomUUID());
           course.setName(dtoCourse.getName());
           course.setDescription(dtoCourse.getDescription());
           course.setCapacity(dtoCourse.getCapacity());


           return repositoryCourse.save(course);
       } catch (Exception e) {
           throw new DtoErrorResponse(e.getMessage());
       }
    }

    public List<EntityCourse> listAll(){
        log.info("ServiceCurso - listar todos - Dados recebidos ");
       try {
           return repositoryCourse.findAll();
       }catch (Exception e){
           throw new DtoErrorResponse(e.getMessage());
       }
    }

    public EntityCourse listById(UUID id){
        log.info("ServiceCurso - listar por id - Dados recebidos: {}", id);
     try {

         Optional<EntityCourse> course = repositoryCourse.findById(id);

         if (course.isPresent()) {
             return course.get();
         } else {
             return null;
         }
     } catch (Exception e) {
         throw new DtoErrorResponse(e.getMessage());
     }
    }

    public EntityCourse changeCourse(DtoCourse dtoCourse, UUID id){
        log.info("ServiceCurso - alterar curso - Dados recebidos: {}, {}", dtoCourse, id);
       try{
        EntityCourse course =listById(id) ;
        if(course !=null){
            course.setName(dtoCourse.getName());
            course.setDescription(dtoCourse.getDescription());
            course.setCapacity(dtoCourse.getCapacity());

            return repositoryCourse.save(course);
        } else{
           return null;
        }
       } catch (Exception e) {
           throw new DtoErrorResponse(e.getMessage());
       }

    }
    public void delete(UUID id){
        log.info("ServiceCurso - deletar curso - Dados recebidos: {}",id);
       try{
        repositoryCourse.deleteById(id);
       } catch (Exception e) {
           throw new DtoErrorResponse(e.getMessage());
       }
    }




}
