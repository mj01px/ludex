package com.ludex.backend.common.web;

public record PageMeta(int page, int perPage, long total, boolean hasMore) {
}
