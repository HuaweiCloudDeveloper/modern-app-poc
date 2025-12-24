
package com.hwc.poc.inventoryservice.application.model;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    private String status;

    private Integer pid;

    private String name;

    private BigDecimal price;

    private Integer amount;

}
