package com.rhys.disruptor.v1;

import com.lmax.disruptor.EventFactory;
import com.rhys.disruptor.LongEvent;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/9/2 2:50 上午
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
