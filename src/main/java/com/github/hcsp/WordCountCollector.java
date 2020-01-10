package com.github.hcsp;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class WordCountCollector implements Collector<String, Map<String,Integer>,Map<String,Integer>> {

    @Override
    public Supplier<Map<String, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, Integer>, String> accumulator() {
        return (Map<String, Integer> count,String word)->{
            count.put(word,count.getOrDefault(word,0)+1);
        };
    }

    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
        return (Map<String, Integer> temp1,Map<String, Integer> temp2)->{
            temp2.forEach((key,value)->{
                temp1.put(key,temp1.getOrDefault(key,0)+value);
            });
            return temp1;
        };
    }

    @Override
    public Function<Map<String, Integer>, Map<String, Integer>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
