package com.example.rest.webservices.exception;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String description;
}
