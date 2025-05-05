package com.escola.mscursos.repository.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "curso")
public class EntityCurso {

    @Id
    private UUID id;

    private String nome;

    private  String descricao;

    private int Capacidade;


}
