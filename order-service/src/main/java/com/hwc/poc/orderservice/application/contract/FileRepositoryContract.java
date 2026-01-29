package com.hwc.poc.orderservice.application.contract;


import com.hwc.poc.orderservice.application.model.File;
import com.hwc.poc.orderservice.application.model.Order;
import org.springframework.web.multipart.MultipartFile;

public interface FileRepositoryContract {
    File upload(MultipartFile file) throws Exception;
}
