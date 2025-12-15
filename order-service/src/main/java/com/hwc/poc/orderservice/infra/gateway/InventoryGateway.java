package com.hwc.poc.orderservice.infra.gateway;

import com.hwc.poc.orderservice.application.contract.InventoryGatewayContract;
import com.hwc.poc.orderservice.infra.gateway.inventory.InventoryClient;
import com.hwc.poc.orderservice.infra.gateway.inventory.InventoryLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InventoryGateway implements InventoryGatewayContract {

	@Autowired
	private InventoryClient InventoryClient;

	@Override
	public String lock(Integer oid, Map<Integer, Integer> productInventoryMap) {
		oid = 123; // for demo only
		return InventoryClient.lock(new InventoryLock(oid, productInventoryMap));
	}

	@Override
	public String unlock(String lockId) {
		lockId = "10"; //for demo only
		return InventoryClient.unlock(lockId);
	}
}
