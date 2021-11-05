package com.delivey.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class Problema {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}
