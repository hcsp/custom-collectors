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
        return (map, key) -> map.merge(key, 1, Integer::sum);
    }

    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    @Override
    public Function<Map<String, Integer>, Map<String, Integer>> finisher() {
        return x -> x;
    }


    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

}
