package com.nminh.bhyt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto<E> {
    private Integer status;
    private String code;
    private String message;
    private E item;
    private List<E> items;
    private Long totalItems;

    public ResponseDto(String code, String message){
        this.code = code;
        this.message = message;
    }

    public ResponseDto(String code, String message, E item){
        this.code = code;
        this.message = message;
        this.item = item;
    }

    public ResponseDto(String code, String message, List<E> items,long totalItems){
        this.code = code;
        this.message = message;
        this.items = items;
        this.totalItems = totalItems;
    }
}
