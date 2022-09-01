package com.rhys.disruptor.v1;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.rhys.disruptor.LongEvent;
import com.rhys.disruptor.LongEventHandler;
import com.rhys.disruptor.LongEventProducer;
import utils.SleepHelper;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

public class DisruptorTest {
    public static void main(String[] args) {
        LongEventFactory factory = new LongEventFactory();
        //must be power of 2
        int ringBufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, ringBufferSize, Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for (long l = 0; l < 100; l++) {
            bb.putLong(0, l);
            producer.onDataOneArg(bb);
            SleepHelper.sleepMilliSeconds(100);
        }

        disruptor.shutdown();
    }
}
