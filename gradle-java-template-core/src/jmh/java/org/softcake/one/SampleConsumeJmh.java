/*
 * Copyright 2017 softcake.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.softcake.one;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 *
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SampleConsumeJmh {

    /**
     * At times you require the test to burn some of the cycles doing nothing.
     * In many cases, you *do* want to burn the cycles instead of waiting.
     *
     * For these occasions, we have the infrastructure support. Blackholes
     * can not only consume the values, but also the time! Run this test
     * to get familiar with this part of JMH.
     *
     * (Note we use static method because most of the use cases are deep
     * within the testing code, and propagating blackholes is tedious).
     *
     * @param args the arguments
     *
     * @throws RunnerException the exception
     */
    public static void main(final String[] args) throws RunnerException {
        IOne one = new One();
        String placeHolder = ".*";
        Options opt = new OptionsBuilder()
                .include(placeHolder + SampleConsumeJmh.class.getSimpleName() + placeHolder)
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    /**
     *
     */
    @Benchmark
    public void consume_0000() {

        Blackhole.consumeCPU(0);
    }

    @Benchmark
    public void consume_0002() {

        Blackhole.consumeCPU(2);
    }

    @Benchmark
    public void consume_0004() {

        Blackhole.consumeCPU(4);
    }

    @Benchmark
    public void consume_0008() {

        Blackhole.consumeCPU(8);
    }

    @Benchmark
    public void consume_0016() {

        Blackhole.consumeCPU(16);
    }

    @Benchmark
    public void consume_0032() {

        Blackhole.consumeCPU(32);
    }

    @Benchmark
    public void consume_0064() {

        Blackhole.consumeCPU(64);
    }

    @Benchmark
    public void consume_0128() {

        Blackhole.consumeCPU(128);
    }

    @Benchmark
    public void consume_0256() {

        Blackhole.consumeCPU(256);
    }

    @Benchmark
    public void consume_0512() {

        Blackhole.consumeCPU(512);
    }

    @Benchmark
    public void consume_1024() {

        Blackhole.consumeCPU(1024);
    }


    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * Note the single token is just a few cycles, and the more tokens
     * you request, then more work is spent (almost linearly)
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar ".*JMHSample_21.*" -w 1 -i 5 -f 1
     *
     * b) Via the Java API:
     */

    /**
     *
     */
    @Benchmark
    public void consume_0001() {

        Blackhole.consumeCPU(1);
    }

}

