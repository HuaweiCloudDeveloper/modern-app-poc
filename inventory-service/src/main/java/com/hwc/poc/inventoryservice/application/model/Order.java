
package com.hwc.poc.inventoryservice.application.model;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

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
