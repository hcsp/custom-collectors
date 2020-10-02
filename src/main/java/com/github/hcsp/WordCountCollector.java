package com.github.hcsp;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class WordCountCollector implements Collector<String, Map<String,Integer>,Map<String,Integer>> {
    @Override
    public Supplier<Map<String,Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String,Integer>, String> accumulator() {
        return (map, s) -> {
            Integer orDefault = map.getOrDefault(s, 0);
            map.put(s,orDefault+1);};
    }

    @Override
    public BinaryOperator<Map<String,Integer>> combiner() {
        return ((map, map2) -> { map.forEach((key,val)-> map2.merge(key,val,Integer::sum));return map2;});
    }

    @Override
    public Function<Map<String,Integer>, Map<String,Integer>> finisher() {
        return m -> m;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    }
}
