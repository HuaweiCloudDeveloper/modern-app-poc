package com.hwc.poc.orderservice.resource.parameters;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Range;


import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationRequest {
    @NotBlank
    private Integer uid;
    @NotBlank
    private BigDecimal totalPrice;

    @NotBlank
    private Integer pid;

    private String name;

    @NotBlank
    @DecimalMin("0")
    private BigDecimal price;

    @NotBlank
    @Range(min = 1, max = 9999, message = "数量不合法")
    private Integer amount;
}