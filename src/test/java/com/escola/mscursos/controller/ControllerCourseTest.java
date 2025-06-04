package com.escola.mscursos.controller;

import com.escola.mscursos.dto.DtoCourse;
import com.escola.mscursos.repository.entity.EntityCourse;
import com.escola.mscursos.service.ServiceCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ControllerCourseTest {

    @InjectMocks
    ControllerCourse controllerCourse;

    @Mock
    ServiceCourse serviceCourse;

    EntityCourse course;

    DtoCourse dtoCourse;

    @BeforeEach
    public void setUp(){
        controllerCourse = new ControllerCourse(serviceCourse);
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
    void register() {
        ResponseEntity<EntityCourse> courseEntity = controllerCourse.register(dtoCourse);
        assertNotNull(courseEntity);
    }

    @Test
    void listAll() {
        ResponseEntity<List<EntityCourse>> listResponseEntity = controllerCourse.listAll();
        assertNotNull(listResponseEntity);
    }

    @Test
    void listById() {
        ResponseEntity<EntityCourse> courseEntity = controllerCourse.listById(course.getId());
        assertNotNull(courseEntity);
    }

    @Test
    void changecourse() {
        UUID id = UUID.randomUUID();
        var controllerCourse1 =controllerCourse.changeCourse(dtoCourse, id);
        assertNotNull(controllerCourse1);
    }

    @Test
    void delete() {
        UUID id = UUID.randomUUID();
        var controllerCourse1 =controllerCourse.delete(id);
        assertNotNull(controllerCourse1);
    }
}