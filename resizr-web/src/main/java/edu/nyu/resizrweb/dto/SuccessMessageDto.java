package edu.nyu.resizrweb.dto;

import lombok.Data;

@Data
public class SuccessMessageDto {
    private String successMessage;

    public SuccessMessageDto(String successMessage) {
        this.successMessage = successMessage;
    }
}
