package com.github.ImagineForge.ImagineFX.Utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionUtils {
    @SafeVarargs
    public static <T> Set<T> setOf(T... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptySet();
        }
        Set<T> set = new HashSet<>();
        Collections.addAll(set, elements);
        return set;
    }
}
