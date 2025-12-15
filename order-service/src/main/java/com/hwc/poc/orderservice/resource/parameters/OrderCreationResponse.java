package com.hwc.poc.orderservice.resource.parameters;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


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
    private String status;
    private Integer pid;
    private String name;
    private BigDecimal price;
    private Integer amount;
}