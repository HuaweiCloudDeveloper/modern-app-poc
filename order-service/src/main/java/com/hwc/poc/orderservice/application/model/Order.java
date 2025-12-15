
package com.hwc.poc.orderservice.application.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Order implements Serializable {

    private Integer oid;

    private Integer uid;

    private BigDecimal totalPrice;

    private Long createTime;

    private OrderStates state;

    private Integer pid;

    private String name;

    public Order() {
        this.setCreateTime(new Date().getTime());
        this.setState(OrderStates.Created);
    }

}
