package com.school.mscourse.dto;

import lombok.Data;

@Data
public class DtoErrorResponse extends  RuntimeException{


    public DtoErrorResponse( String mensagen ){
        super(mensagen);


    }

}
