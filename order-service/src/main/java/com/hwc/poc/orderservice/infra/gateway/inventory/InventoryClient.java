package com.hwc.poc.orderservice.infra.gateway.inventory;

import com.hwc.poc.orderservice.infra.config.FeignHttpsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${hwc.poc.inventory.url}", name = "inventory", configuration = FeignHttpsConfig.class)
public interface InventoryClient {

	@RequestMapping(method = RequestMethod.POST, value = "/inventory")
	String add(@RequestBody InventoryRequest inventoryRequest);

	@RequestMapping(method = RequestMethod.GET, value = "/inventory/{oid}")
	String find(@PathVariable("oid") String oid);

	@RequestMapping(method = RequestMethod.POST, value = "/inventory/notify")
	String notifyInventory(@RequestBody InventoryRequest inventoryRequest);
}
