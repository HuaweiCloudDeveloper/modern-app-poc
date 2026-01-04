package com.hwc.poc.orderservice.infra.gateway;

import com.hwc.poc.orderservice.application.contract.InventoryGatewayContract;
import com.hwc.poc.orderservice.application.model.Order;
import com.hwc.poc.orderservice.infra.gateway.inventory.InventoryClient;
import com.hwc.poc.orderservice.infra.gateway.inventory.InventoryRequest;
import com.hwc.poc.orderservice.infra.gateway.inventory.InventoryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InventoryGateway implements InventoryGatewayContract {

	protected static final ModelMapper mapper = new ModelMapper();

	@Autowired
	private InventoryClient InventoryClient;

	@Override
	public String lock(Order order) {

		InventoryRequest inventoryRequest = mapper.map(order, InventoryRequest.class);
		return InventoryClient.add(inventoryRequest);
	}

	@Override
	public String unlock(String lockId) {
		lockId = "10"; //for demo only
		return InventoryClient.find(lockId);
	}
}
