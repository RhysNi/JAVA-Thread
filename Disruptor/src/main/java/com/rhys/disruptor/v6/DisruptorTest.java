package com.rhys.disruptor.v6;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.rhys.disruptor.LongEvent;
import com.rhys.disruptor.LongEventHandler;
import utils.SleepHelper;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorTest {

    public static void main(String[] args) {
        //Specify the of the ring buffer,must be power of 2.
        int bufferSize = 1024;

        //Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, Executors.defaultThreadFactory(), ProducerType.MULTI, new SleepingWaitStrategy());

        //Connect the handlers
        EventHandler h1 = (event, sequence, end) -> System.out.println("消费者出异常");
        disruptor.handleEventsWith(h1);

        disruptor.handleExceptionsFor(h1).with(new ExceptionHandler<LongEvent>() {
            @Override
            public void handleEventException(Throwable throwable, long l, LongEvent longEvent) {
                throwable.printStackTrace();
            }

            @Override
            public void handleOnStartException(Throwable throwable) {
                System.out.println("Exception Start to Handle!");
            }

            @Override
            public void handleOnShutdownException(Throwable throwable) {
                System.out.println("Exception End to Handle!");
            }
        });

        //Start the Disruptor,start all threads running
        disruptor.start();

        //Get the ring buffer form the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //========================================================================
        final int threadCount = 1;
        CyclicBarrier barrier = new CyclicBarrier(threadCount);
        ExecutorService service = Executors.newCachedThreadPool();
        for (long i = 0; i < threadCount; i++) {
            final long threadNum = i;
            service.submit(() -> {
                System.out.println("Thread " + threadNum + "ready to start!");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < 10; j++) {
                    ringBuffer.publishEvent((event, sequence) -> {
                        event.setValue(threadNum);
                        System.out.println("生产了" + threadNum);
                    });
                }
            });
        }

        service.shutdown();
        //disruptor.shutdown();
        SleepHelper.sleepSeconds(3);
        System.out.println(LongEventHandler.count);
    }
}
