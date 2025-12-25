package com.hwc.poc.orderservice.infra.gateway.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryClientFallback implements InventoryClient {

	@Override
	public String add(InventoryRequest lock) {
		log.warn("InventoryClientFallback");
		return null;
	}

	@Override
	public String find(String oid) {
		log.warn("InventoryClientFallback");
		return "";
	}

}
