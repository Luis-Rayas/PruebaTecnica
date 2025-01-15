package com.estafanini.pruebatecnica.LRCRR.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseResponse<T> {
    private boolean error;
    private List<String> messages;
    private T data;

    public BaseResponse() {
        this.error = false;
        this.messages = new ArrayList<>();
        this.data = null;
    }

    public BaseResponse(boolean error, List<String> messages, T data) {
        this.error = error;
        this.messages = messages;
        this.data = data;
    }
}
