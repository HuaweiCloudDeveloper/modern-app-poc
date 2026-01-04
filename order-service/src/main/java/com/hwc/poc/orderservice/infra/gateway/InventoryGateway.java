package com.hwc.poc.orderservice.infra.gateway;

import com.hwc.poc.orderservice.application.contract.InventoryGatewayContract;
import com.hwc.poc.orderservice.application.model.Order;
import com.hwc.poc.orderservice.infra.gateway.inventory.InventoryClient;
import com.hwc.poc.orderservice.infra.gateway.inventory.InventoryRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryGateway implements InventoryGatewayContract {

	protected static final ModelMapper mapper = new ModelMapper();

	@Autowired
	private InventoryClient inventoryClient;

	@Override
	public String lock(Order order) {

		InventoryRequest inventoryRequest = mapper.map(order, InventoryRequest.class);
		return inventoryClient.add(inventoryRequest);
	}

	@Override
	public String unlock(String lockId) {
		lockId = "10"; //for demo only
		return inventoryClient.find(lockId);
	}

	@Override
	public String queryInventory(Integer oid) {
		return inventoryClient.find(oid.toString());
	}

	@Override
	public String notifyInventory(Order order) {
		InventoryRequest inventoryRequest = mapper.map(order, InventoryRequest.class);
		return inventoryClient.notifyInventory(inventoryRequest);
	}
}
