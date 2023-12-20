package com.example.demo.collection;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.collections.MapUtils.isEmpty;

/**
 * @author caozhixin
 * @date 2023/12/20 10:39
 */
public class CollectionToMap {
    public static void main(String[] args) {
        // 创建一个 OrderItem 对象的集合
        List<OrderItem> coll = new ArrayList<>();

        // 添加一些 OrderItem 对象到集合中
        coll.add(new OrderItem(1L, 20.0, "apple"));
        coll.add(new OrderItem(2L, 30.0, "banana"));

        Collection<OrderItem> collection = coll;
        Set<OrderItem> set = toSet(collection);

        List<OrderItem> orderItems = toList(set);
        System.out.println(orderItems);

        Map<Long, OrderItem> map = toMap(set, OrderItem::getOrderId);
        System.out.println(map);

        Map<Long, Double> orderId2Price = convertMapValue(map, (id, item) -> item.getActPrice());
        System.out.println(orderId2Price);
        Map<Long, String> orderId2Name = convertMapValue(map, (id, item) -> id + item.getName());
        System.out.println(orderId2Name);

        List<Long> orderIdsFromList = mapToList(orderItems, item -> item.getOrderId());
        System.out.println(orderIdsFromList);
        Set<Long> orderIdsFromSet = mapToSet(set, item -> item.getOrderId());
        System.out.println(orderIdsFromSet);
    }

    public static <T, R> List<R> map(List<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> map(Set<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <T, R> List<R> mapToList(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> mapToSet(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <K, V, C> Map<K, C> convertMapValue(Map<K, V> originMap, BiFunction<K, V, C> valueConverter) {
        return convertMapValue(originMap, valueConverter, pickSecond());
    }

    public static <K, V, C> Map<K, C> convertMapValue(Map<K, V> map, BiFunction<K, V, C> valueFunction, BinaryOperator<C> mergeFunction) {
        if (isEmpty(map)) {
            return new HashMap<>();
        }
        return map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> valueFunction.apply(e.getKey(), e.getValue()), mergeFunction));
    }

    public static <K, T> Map<K, T> toMap(Collection<T> collection, Function<? super T, ? extends K> keyMapper) {
        return toMap(collection, keyMapper, Function.identity());
    }

    public static <T, K, V> Map<K, V> toMap(Collection<T> collection,
                                            Function<? super T, ? extends K> keyFunction,
                                            Function<? super T, ? extends V> valueFunction) {
        return toMap(collection, keyFunction, valueFunction, pickFirst());
    }

    public static <T, K, V> Map<K, V> toMap(Collection<T> collection,
                                            Function<? super T, ? extends K> keyFunction,
                                            Function<? super T, ? extends V> valueFunction,
                                            BinaryOperator<V> mergeFunction) {
        if (CollectionUtils.isEmpty(collection)) {
            return new HashMap<>(0);
        }

        return collection.stream().collect(Collectors.toMap(keyFunction, valueFunction, mergeFunction));
    }

    public static <T> BinaryOperator<T> pickFirst() {
        return (k1, k2) -> k1;
    }

    public static <T> BinaryOperator<T> pickSecond() {
        return (k1, k2) -> k2;
    }

    public static <T> List<T> toList(Collection<T> collection) {
        if (collection == null) {
            return new ArrayList<>();
        }
        if (collection instanceof List) {
            return (List<T>) collection;
        }
        return collection.stream().collect(Collectors.toList());
    }

    public static <T> Set<T> toSet(Collection<T> collection) {
        if (collection == null) {
            return new HashSet<>();
        }
        if (collection instanceof Set) {
            return (Set<T>) collection;
        }
        return collection.stream().collect(Collectors.toSet());
    }

    @Data
    @AllArgsConstructor
    static class OrderItem {
        private Long orderId;
        private Double actPrice;
        private String name;
    }
}
