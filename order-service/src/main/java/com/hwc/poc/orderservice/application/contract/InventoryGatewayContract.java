package com.hwc.poc.orderservice.application.contract;

import java.util.Map;

public interface InventoryGatewayContract {
    String lock(Integer oid, Map<Integer, Integer> productInventoryMap);

    String unlock(String lockId);
}
