package com.kiki.newsbe.utils.validation;

import com.kiki.newsbe.utils.exceptions.BadRequestException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Validate {
    // Cek null atau empty tergantung tipe datanya
    public static <T> void c(T object, Map<String, Function<T, ?>> fieldExtractors) {
        List<String> errors = new ArrayList<>();

        for (Map.Entry<String, Function<T, ?>> entry : fieldExtractors.entrySet()) {
            Object value = entry.getValue().apply(object);

            if (isNullOrEmpty(value)) {
                errors.add(entry.getKey());
            }
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(errors);
        }
    }

    // Cek apakah value null atau kosong berdasarkan tipe data
    private static boolean isNullOrEmpty(Object value) {
        if (value == null) return true;
        if (value instanceof String str) return str.isEmpty();
        if (value instanceof Collection<?> col) return col.isEmpty();
        if (value instanceof Map<?, ?> map) return map.isEmpty();
        if (value instanceof Number num) return isZeroOrNegative(num);
        if (value instanceof Boolean bool) return !bool; // false dianggap kosong
        if (value instanceof Enum<?>) return false; // Enum valid jika tidak null
        if (value instanceof LocalDate || value instanceof LocalDateTime) return false; // Tanggal valid jika tidak null
        return isEmptyObject(value);
    }

    // Cek apakah angka bernilai 0 atau negatif
    private static boolean isZeroOrNegative(Number num) {
        if (num instanceof Integer i) return i <= 0;
        if (num instanceof Double d) return d <= 0.0;
        if (num instanceof Float f) return f <= 0.0f;
        if (num instanceof BigDecimal bd) return bd.compareTo(BigDecimal.ZERO) <= 0;
        return false;
    }

    // Cek apakah objek kosong (tidak ada field yang terisi)
    private static boolean isEmptyObject(Object obj) {
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(obj) != null) {
                    return false; // Ada nilai yang diisi, tidak kosong
                }
            }
        } catch (IllegalAccessException ignored) {
        }
        return true; // Semua field null, objek kosong
    }
}
