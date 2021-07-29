package com.ntsi.messageprocessor.userType;

import java.util.*;

public interface DeepCopyable {
    static <T extends DeepCopyable> T deepCopy(T copyable) {
        if (copyable == null) {
            return null;
        }
        return (T) copyable.deepCopy();
    }

    static <T extends DeepCopyable> TreeSet<T> deepCopyTreeSet(TreeSet<T> copyable) {
        if (copyable == null) {
            return null;
        }
        TreeSet copy = new TreeSet<>();
        for (T item : copyable) {
            copy.add(item.deepCopy());
        }
        return copy;
    }

    static <T extends DeepCopyable> Set<T> deepCopySet(Set<T> copyable) {
        if (copyable == null) {
            return null;
        }
        HashSet copy = new HashSet<>();
        for (T item : copyable) {
            copy.add(item.deepCopy());
        }
        return copy;
    }

    static <K, T extends DeepCopyable> Map<K, T> deepCopyMap(Map<K, T> copyable) {
        if (copyable == null) {
            return null;
        }
        HashMap copy = new HashMap<>();
        for (Map.Entry<K, T> entry : copyable.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().deepCopy());
        }
        return copy;
    }

    static <T extends DeepCopyable> List<T> deepCopyList(List<T> copyable) {
        if (copyable == null) {
            return null;
        }
        List copy = new ArrayList();
        for (T item : copyable) {
            copy.add(item.deepCopy());
        }
        return copy;
    }

    Object deepCopy();
}
