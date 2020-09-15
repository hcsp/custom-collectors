package com.github.hcsp;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class WordCountCollector implements Collector<String, Map<String, Integer>, Map<String, Integer>> {

    /**
     * 供应商
     */
    @Override
    public Supplier<Map<String, Integer>> supplier() {
        return HashMap::new;
    }

    /**
     * 累加器
     */
    @Override
    public BiConsumer<Map<String, Integer>, String> accumulator() {
        return (map, s) -> map.put(s, map.getOrDefault(s, 0) + 1);
    }

    /**
     * 组合器
     */
    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
        return (map1, map2) -> {
            map2.forEach((k, v) -> map1.merge(k, v, Integer::sum));
            return map1;
        };
    }

    /**
     * 结束
     */
    @Override
    public Function<Map<String, Integer>, Map<String, Integer>> finisher() {
        return map -> map;
    }

    /**
     * 特点
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    }
}
