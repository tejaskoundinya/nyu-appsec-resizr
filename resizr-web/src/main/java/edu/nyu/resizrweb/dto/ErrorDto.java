package edu.nyu.resizrweb.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private String errorMessage;

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}