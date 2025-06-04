package com.escola.mscursos.service;

import com.escola.mscursos.dto.DtoCourse;
import com.escola.mscursos.dto.DtoErrorResponse;
import com.escola.mscursos.repository.RepositoryCourse;
import com.escola.mscursos.repository.entity.EntityCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceCourseTest {

    @InjectMocks
    ServiceCourse  serviceCourse;

    @Mock
    RepositoryCourse repositoryCourse;

    EntityCourse course;

    DtoCourse dtoCourse;

    @BeforeEach
    public void setUp(){
        serviceCourse = new ServiceCourse(repositoryCourse);
       dtoCourse = DtoCourse.builder()
               .name("Curso Java")
               .description("Curso em java com spring")
               .capacity(30).build();
       course = EntityCourse.builder()
               .id(UUID.randomUUID())
               .name(dtoCourse.getName())
               .description(dtoCourse.getDescription())
               .capacity(dtoCourse.getCapacity()).build();

    }


    @Test
    void registerTest() {

        when(repositoryCourse.save(any())).thenReturn(course);

        EntityCourse entityCourse =serviceCourse.register(dtoCourse);
        assertEquals(entityCourse, course);
        assertEquals(entityCourse.getName(), dtoCourse.getName());

    }

    @Test
    void registerExceptionTest(){
        when(repositoryCourse.save(any()))
                .thenThrow(new RuntimeException("Erro simulado no save"));

        DtoErrorResponse thrown = assertThrows(
                DtoErrorResponse.class,
                () -> serviceCourse.register(dtoCourse)
        );
        assertEquals("Erro simulado no save", thrown.getMessage());
    }

    @Test
    void listAllTest() {
        when(repositoryCourse.findAll()).thenReturn(Collections.singletonList(course));

        List<EntityCourse> courseEntities = serviceCourse.listAll();

        assertEquals(Collections.singletonList(course), courseEntities);
        assertEquals(1, courseEntities.size());

    }
    @Test
    void listAllExceptionTest(){
        when(repositoryCourse.findAll())
                .thenThrow(new RuntimeException("Erro simulado no save"));

        DtoErrorResponse thrown = assertThrows(
                DtoErrorResponse.class,
                () -> serviceCourse.listAll()
        );
        assertEquals("Erro simulado no save", thrown.getMessage());
    }

    @Test
    void listByIdTest() {
        when(repositoryCourse.findById(any())).thenReturn(Optional.of(course));

        EntityCourse entityCourse = serviceCourse.listById(course.getId());

        assertEquals(entityCourse, course);
    }

    @Test
    void listByIdExceptionTest(){
        when(repositoryCourse.findById(any()))
                .thenThrow(new RuntimeException("Erro simulado no save"));

        DtoErrorResponse thrown = assertThrows(
                DtoErrorResponse.class,
                () -> serviceCourse.listById(course.getId())
        );
        assertEquals("Erro simulado no save", thrown.getMessage());
    }
    @Test
    void changecourseTest() {
        when(repositoryCourse.findById(any())).thenReturn(Optional.of(course));
        EntityCourse entityCourse = serviceCourse.listById(course.getId());
        when(repositoryCourse.save(any())).thenReturn(course);
        EntityCourse entityCourse1 = serviceCourse.changeCourse(dtoCourse, entityCourse.getId());
        assertEquals(entityCourse1, course);
    }
    @Test
    void changecourseNullTest() {

        EntityCourse entityCourse1 = serviceCourse.changeCourse(dtoCourse, course.getId());
        assertEquals(entityCourse1, null);
    }
    @Test
    void changecourseExceptionTest(){
        when(repositoryCourse.findById(any()))
                .thenThrow(new RuntimeException("Erro simulado no save"));

        DtoErrorResponse thrown = assertThrows(
                DtoErrorResponse.class,
                () -> serviceCourse.changeCourse(dtoCourse, course.getId())
        );
        assertEquals("Erro simulado no save", thrown.getMessage());
    }

    @Test
    void deleteTest() {
        serviceCourse.delete(course.getId());
    }
    @Test
    void deleteExceptionTest() {
        UUID id = UUID.randomUUID();

        doThrow(new RuntimeException("Erro simulado no delete"))
                .when(repositoryCourse)
                .deleteById(id);

        DtoErrorResponse thrown = assertThrows(
                DtoErrorResponse.class,
                () -> serviceCourse.delete(id)
        );
        assertEquals("Erro simulado no delete", thrown.getMessage());
    }
}