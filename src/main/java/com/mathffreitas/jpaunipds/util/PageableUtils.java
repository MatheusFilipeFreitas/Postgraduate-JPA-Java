package com.mathffreitas.jpaunipds.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public final class PageableUtils {

    private PageableUtils() {
    }

    public static Pageable sanitize(Pageable pageable) {
        return sanitize(pageable, "id");
    }

    public static Pageable sanitize(Pageable pageable, String defaultSortProperty) {
        if (pageable.getSort().isUnsorted()) {
            return pageable;
        }

        List<Sort.Order> validOrders = new ArrayList<>();
        pageable.getSort().forEach(order -> {
            if (isValidPropertyName(order.getProperty())) {
                validOrders.add(order);
            }
        });

        if (validOrders.isEmpty()) {
            return PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(defaultSortProperty).ascending());
        }

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(validOrders));
    }

    private static boolean isValidPropertyName(String property) {
        return property != null
                && !property.isBlank()
                && !property.contains("[")
                && !property.contains("]")
                && !"string".equalsIgnoreCase(property);
    }
}
