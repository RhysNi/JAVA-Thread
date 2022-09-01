package com.rhys.disruptor;

import com.lmax.disruptor.*;
import lombok.Data;

import java.nio.ByteBuffer;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/9/2 3:27 上午
 */
@Data
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    //Version-1
    public void onData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    //Version-2 使用translator
    //迎合lambda写法 没有tay-catch
    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR_ONE_ARG = (longEvent, sequence, byteBuffer) -> longEvent.setValue(byteBuffer.getLong(0));

    private static final EventTranslatorTwoArg<LongEvent, ByteBuffer, ByteBuffer> TRANSLATOR_TWO_ARG = (longEvent, sequence, l1, l2) -> longEvent.setValue(l1.getLong(0) + l2.getLong(0));

    private static final EventTranslatorThreeArg<LongEvent, ByteBuffer, ByteBuffer, ByteBuffer> TRANSLATOR_THREE_ARG = (longEvent, sequence, l1, l2, l3) -> longEvent.setValue(l1.getLong(0) + l2.getLong(0) + l3.getLong(0));

    private static final EventTranslatorVararg<LongEvent> TRANSLATOR_VARARG = (longEvent, sequence, objects) -> {
        long result = 0;
        for (Object o : objects) {
            long l = (long) o;
            result += l;
        }
        longEvent.setValue(result);
    };

    public void onDataOneArg(ByteBuffer byteBuffer) {
        ringBuffer.publishEvent(TRANSLATOR_ONE_ARG, byteBuffer);
    }

    public void onDataTwoArg(ByteBuffer buffer1, ByteBuffer buffer2) {
        ringBuffer.publishEvent(TRANSLATOR_TWO_ARG, buffer1, buffer2);
    }

    public void onDataThreeArg(ByteBuffer buffer1, ByteBuffer buffer2, ByteBuffer buffer3) {
        ringBuffer.publishEvent(TRANSLATOR_THREE_ARG, buffer1, buffer2, buffer3);
    }

    public void onDataVarArg(ByteBuffer buffer1, ByteBuffer buffer2, ByteBuffer buffer3, ByteBuffer buffer4) {
        ringBuffer.publishEvent(TRANSLATOR_VARARG, buffer1.getLong(0), buffer2.getLong(0), buffer3.getLong(0), buffer4.getLong(0));
    }
}
