package com.hwc.poc.orderservice.resource.parameters;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationResponse {
    private Integer oid;
    private Integer uid;
    private BigDecimal totalPrice;
    private Long createTime;
    private String state;
    private String name;
}