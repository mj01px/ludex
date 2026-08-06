package com.ludex.backend.common.web;

import java.util.List;

public record ErrorResponse(ErrorBody error) {

    public record ErrorBody(String code, String message, List<FieldIssue> details) {
    }

    public record FieldIssue(String field, String issue) {
    }

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(new ErrorBody(code, message, List.of()));
    }

    public static ErrorResponse of(String code, String message, List<FieldIssue> details) {
        return new ErrorResponse(new ErrorBody(code, message, details));
    }
}
