package com.github.hcsp;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class WordCountCollector implements Collector<String, HashMap<String, Integer>, Map<String, Integer>> {
    WordCountCollector() {
    }

    @Override
    public Supplier<HashMap<String, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<HashMap<String, Integer>, String> accumulator() {
        return (map, string) -> map.merge(string, 1, Integer::sum);
    }

    @Override
    public BinaryOperator<HashMap<String, Integer>> combiner() {
        return (map1, map2) -> {
            map1.forEach((k, v) -> map2.merge(k, v, Integer::sum));
            return map2;
        };
    }

    @Override
    public Function<HashMap<String, Integer>, Map<String, Integer>> finisher() {
        return identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
    }

    static <T extends R, R> Function<T, R> identity() {
        return t -> t;
    }
}
