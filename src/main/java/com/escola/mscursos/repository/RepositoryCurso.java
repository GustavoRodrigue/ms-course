package com.escola.mscursos.repository;

import com.escola.mscursos.repository.entity.EntityCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryCurso  extends JpaRepository<EntityCurso, UUID> {

}
