package com.github.hcsp;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class WordCountCollector implements Collector<String, Map<String, Integer>, Map<String, Integer>> {

    @Override
    public Supplier<Map<String, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, Integer>, String> accumulator() {
        return (map, k) -> map.put(k, map.getOrDefault(k, 0) + 1);
    }

    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
        return (r1, r2) -> {
            r1.putAll(r2);
            return r1;
        };
    }

    @Override
    public Function<Map<String, Integer>, Map<String, Integer>> finisher() {
        return m -> m;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    }
}
