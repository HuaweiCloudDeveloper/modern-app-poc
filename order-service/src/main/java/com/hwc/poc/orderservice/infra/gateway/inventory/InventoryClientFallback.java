package com.hwc.poc.orderservice.infra.gateway.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryClientFallback implements InventoryClient {

	@Override
	public String lock(InventoryLock lock) {
		log.warn("InventoryClientFallback");
		return "";
	}

	@Override
	public String unlock(String lockId) {
		log.warn("InventoryClientFallback");
		return "";
	}

}
