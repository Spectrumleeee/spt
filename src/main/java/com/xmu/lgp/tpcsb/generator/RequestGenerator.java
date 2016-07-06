/**
 * Copyright (c) 2016, TP-Link Co.,Ltd.
 * Author:  liguangpu <liguangpu@tp-link.net>
 * Created: 2016-3-25
 *
 */
package com.xmu.lgp.tpcsb.generator;

import java.util.concurrent.atomic.AtomicInteger;

import com.tplink.cloud.api.Request;

public abstract class RequestGenerator extends Generator<Request> {

    // all the id in multi-thread should be unique
    protected static AtomicInteger id = new AtomicInteger(0);

    @Override
    public Request nextValue() {
        id.compareAndSet(Integer.MAX_VALUE, 0);
        return buildRequest();
    }

    public Integer getCount() {
        return id.get();
    }

    public abstract Request buildRequest();

}
