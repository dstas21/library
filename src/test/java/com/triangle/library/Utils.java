package com.triangle.library;

import java.util.Random;

/**
 * Тестовые утилиты
 */
public class Utils {

    public static Long generateId() {
        return new Random().nextLong();
    }
}
