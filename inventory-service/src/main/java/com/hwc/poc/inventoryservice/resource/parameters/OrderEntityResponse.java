package com.hwc.poc.inventoryservice.resource.parameters;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntityResponse {
    private Integer oid;
    private Integer uid;
    private BigDecimal totalPrice;
    private Long createTime;
    private String state;
    private Integer pid;
    private String name;
    private BigDecimal price;
    private Integer amount;
}