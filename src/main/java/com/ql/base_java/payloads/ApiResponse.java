package com.ql.base_java.payloads;

import com.ql.base_java.constant.Constants;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;


@Data
@Builder
@NoArgsConstructor
public class ApiResponse {

    private int code;

    private boolean status;

    private String message;

    @Builder.Default
    private Object data = Collections.emptyMap();

    public ApiResponse(int code, boolean status, String message, Object data) {
        this.code = code;
        this.status = status;
        this.message = message != null ? message : Constants.RESPONSE.get(code);
        this.data = data;
    }

}
