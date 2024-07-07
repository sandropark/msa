package com.sandro.msa.saga.orchestration.event;

public interface Topic {
    String CREATE_ORDER = "create-order";
    String KITCHEN_VALIDATE_RESULT = "kitchen-validate-result";
}
