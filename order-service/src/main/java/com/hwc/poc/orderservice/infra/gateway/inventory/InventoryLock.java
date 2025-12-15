package com.hwc.poc.orderservice.infra.gateway.inventory;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class InventoryLock {
	private Integer oid;
	private Map<Integer, Integer> productInventoryMap;
}
