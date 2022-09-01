package com.rhys.disruptor;

import org.openjdk.jmh.annotations.*;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/9/1 2:21 上午
 */
public class ParallelStreamCompTest {

    @Benchmark
    @Warmup(iterations = 1, time = 3)
    @Fork(5)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 1, time = 3)
    public void foreach() {
        ParallelStreamComp.foreach();
    }
}