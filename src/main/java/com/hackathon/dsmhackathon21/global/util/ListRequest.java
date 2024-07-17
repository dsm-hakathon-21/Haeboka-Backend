package com.hackathon.dsmhackathon21.global.util;

import java.util.List;

public record ListRequest<T>(
        List<T> list
) {
}
