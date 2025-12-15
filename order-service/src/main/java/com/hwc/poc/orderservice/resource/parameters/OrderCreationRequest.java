package com.hwc.poc.orderservice.resource.parameters;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationRequest {

    private String name;

    @NotBlank
    private BigDecimal totalPrice;
}