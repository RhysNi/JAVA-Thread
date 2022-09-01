package com.rhys.disruptor;

import lombok.Data;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/9/2 2:50 上午
 */
@Data
public class LongEvent {
    private long value;

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                "}";
    }
}
