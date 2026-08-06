package com.ludex.backend.common.web;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public record PagedResponse<T>(List<T> data, PageMeta meta, String requestId) {

    public static <T> PagedResponse<T> of(Page<T> page) {
        var meta = new PageMeta(page.getNumber(), page.getSize(), page.getTotalElements(), page.hasNext());
        return new PagedResponse<>(page.getContent(), meta, UUID.randomUUID().toString());
    }
}
