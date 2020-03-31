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
import java.util.stream.Collectors;

public class WordCountCollector implements Collector<String, Map<String, Integer>, Map<String, Integer>> {
    @Override
    public Supplier<Map<String, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, Integer>, String> accumulator() {
        return (stringIntegerMap, s) -> stringIntegerMap.put(s, stringIntegerMap.getOrDefault(s, 0) + 1);
    }

    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
        return (stringIntegerMap, stringIntegerMap2) -> {
            stringIntegerMap.putAll(stringIntegerMap2);
            return stringIntegerMap;
        };
    }

    @Override
    public Function<Map<String, Integer>, Map<String, Integer>> finisher() {
        return x->x;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
