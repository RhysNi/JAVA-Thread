package com.rhys.disruptor;

import com.lmax.disruptor.EventHandler;
import com.rhys.disruptor.LongEvent;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/9/2 2:51 上午
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public static long count = 0;

    /**
     * @param longEvent
     * @param sequence   RingBuffer的序号
     * @param endOfBatch 是否为最后一个元素
     * @throws Exception
     **/
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        count++;
        System.out.println("[" + Thread.currentThread().getName() + "]" + longEvent + "序号：" + sequence);
    }
}
