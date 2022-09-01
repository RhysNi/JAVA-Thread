package com.rhys.disruptor.v2;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.rhys.disruptor.LongEvent;
import com.rhys.disruptor.LongEventHandler;
import com.rhys.disruptor.LongEventProducer;
import utils.SleepHelper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

public class DisruptorTest {

    public static void main(String[] args) throws IOException {
        //Specify the of the ring buffer,must be power of 2.
        int bufferSize = 1024;

        //Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, Executors.defaultThreadFactory());

        //Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        //Start the Disruptor,start all threads running
        disruptor.start();

        //Get the ring buffer form the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer a = ByteBuffer.allocate(8);
        ByteBuffer b = ByteBuffer.allocate(8);
        ByteBuffer c = ByteBuffer.allocate(8);
        ByteBuffer d = ByteBuffer.allocate(8);

        for (long l = 0; l < 100; l++) {
            a.putLong(0, l);
            b.putLong(0, l);
            c.putLong(0, l);
            d.putLong(0, l);

            // producer.onDataOneArg(a);
            // producer.onDataTwoArg(a, b);
            // producer.onDataThreeArg(a, b, c);
            producer.onDataVarArg(a, b, c, d);
            SleepHelper.sleepMilliSeconds(100);
        }
        disruptor.shutdown();
    }
}
