package com.delani.homegrid.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private String requestTime;
    private String requestType;
    private String referenceId;
    private boolean status;
    private String message;
    private T data;
}
