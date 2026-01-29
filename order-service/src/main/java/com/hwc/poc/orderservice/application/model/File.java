
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
public class File implements Serializable {

    private Long id;

    private String fileName;

    private Long fileSize;

    private byte[] fileContent;

    private Date uploadTime;

    public File() {
        this.uploadTime = new Date();

    }

}
