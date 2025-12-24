package com.hwc.poc.inventoryservice.resource.parameters;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntityRequest {
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