package com.hwc.poc.orderservice.infra.gateway.inventory;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {
    @NotBlank
    public Integer oid;
    public Integer uid;
    public BigDecimal totalPrice;
    public Long createTime;
    public String status;
    public Integer pid;
    public String name;
    public BigDecimal price;
    public Integer amount;
}