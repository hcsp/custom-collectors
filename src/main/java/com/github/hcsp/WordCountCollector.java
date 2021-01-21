package com.github.hcsp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
        return (map, str) -> {
            map.put(str, map.getOrDefault(str, 0) + 1);
        };
    }

    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
        return (m1, m2) -> {
            for (Map.Entry<String, Integer> e : m2.entrySet()) {
                final String k = e.getKey();
                m1.put(k, m1.getOrDefault(k, 0) + e.getValue());
            }
            return m1;
        };
    }

    @Override
    public Function<Map<String, Integer>, Map<String, Integer>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
