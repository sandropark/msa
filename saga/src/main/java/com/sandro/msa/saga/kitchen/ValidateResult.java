package com.sandro.msa.saga.kitchen;

import com.sandro.msa.saga.util.ObjectMapperUtil;

public record ValidateResult(Long orderId, boolean accepted) {

    @Override
    public String toString() {
        return ObjectMapperUtil.writeValueAsString(this);
    }
}
