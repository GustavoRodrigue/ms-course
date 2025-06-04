package com.school.mscourse.repository;

import com.school.mscourse.repository.entity.EntityCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryCourse extends JpaRepository<EntityCourse, UUID> {

}
