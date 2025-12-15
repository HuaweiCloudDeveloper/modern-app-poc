package com.hwc.poc.orderservice.infra.gateway.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${hwc.poc.inventory.url}", name = "inventory", fallback = InventoryClientFallback.class)
public interface InventoryClient {

	@RequestMapping(method = RequestMethod.POST, value = "/inventories/lock")
	String lock(InventoryLock lock);

	@RequestMapping(method = RequestMethod.PUT, value = "/inventories/lock/{lockId}")
	String unlock(@PathVariable("lockId") String lockId);

}
