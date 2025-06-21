package com.delani.homegrid.payload;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ApiResponseUtil {
    public static <T> ApiResponse<T> buildSuccessResponse(T data, String message) {
        return ApiResponse.<T>builder()
                .requestTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .requestType("Outbound")
                .referenceId(UUID.randomUUID().toString())
                .status(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> buildErrorResponse(String message) {
        return ApiResponse.<T>builder()
                .requestTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .requestType("Outbound")
                .referenceId(UUID.randomUUID().toString())
                .status(false)
                .message(message)
                .data(null)
                .build();
    }
}
