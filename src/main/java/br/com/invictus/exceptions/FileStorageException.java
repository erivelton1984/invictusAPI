package br.com.invictus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class FileStorageException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public FileStorageException(String ex){
        super(ex);
    }

    public FileStorageException(String ex, Throwable cause){
        super(ex, cause);
    }
}
