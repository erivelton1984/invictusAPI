package br.com.invictus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileStorageException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public MyFileStorageException(String ex){
        super(ex);
    }

    public MyFileStorageException(String ex, Throwable cause){
        super(ex, cause);
    }
}
