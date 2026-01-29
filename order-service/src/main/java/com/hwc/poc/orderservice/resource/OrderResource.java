package com.hwc.poc.orderservice.resource;


import com.hwc.poc.orderservice.application.OrderService;
import com.hwc.poc.orderservice.application.model.File;
import com.hwc.poc.orderservice.application.model.Order;
import com.hwc.poc.orderservice.resource.parameters.OrderCreationRequest;
import com.hwc.poc.orderservice.resource.parameters.OrderCreationResponse;
import com.hwc.poc.orderservice.resource.parameters.OrderUpdateRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/orders")
public class OrderResource {

    private static final Logger logger = LoggerFactory.getLogger(OrderResource.class);
    protected static final ModelMapper mapper = new ModelMapper();
    private static final Integer DEFUALT_UID = 1;

    private OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, headers = "Accept=application/json")
    public OrderCreationResponse create(OrderCreationRequest request) {

        Order param = mapper.map(request, Order.class);
        param.setUid(DEFUALT_UID);

        Order result = orderService.placeOrder(param);

        return mapper.map(result, OrderCreationResponse.class);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, headers = "Accept=application/json")
    public int update(OrderUpdateRequest request) {

        Order param = mapper.map(request, Order.class);
        param.setUid(DEFUALT_UID);

        return orderService.updateOrder(param);
    }

    @RequestMapping(value = "/{oid}", method = RequestMethod.GET, headers = "Accept=application/json")
    public OrderCreationResponse getCurrentOrder(@PathVariable("oid") Integer orderId) {
        final Order order = orderService.getOrderById(orderId);
        return mapper.map(order, OrderCreationResponse.class);
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.POST, headers = "Accept=application/json")
    public String createInventory(OrderCreationResponse request) {

        Order order = mapper.map(request, Order.class);
        return orderService.lockInventory(order);
    }


    @RequestMapping(value = "/inventory/{oid}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String queryInventory(@PathVariable("oid") Integer oid) {
        return orderService.queryInventory(oid);
    }

    @RequestMapping(value = "/inventory/notify", method = RequestMethod.POST, headers = "Accept=application/json")
    public String notifyInventory(OrderCreationResponse request) {

        Order order = mapper.map(request, Order.class);
        return orderService.notifyInventory(order);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        // 校验文件是否为空
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("上传失败！文件不能为空");
        }

        try {
            // 调用服务层保存文件
            File fileEntity = orderService.uploadFile(file);
            return ResponseEntity.ok()
                    .body("File uploaded successfully! File ID:" + fileEntity.getId() +
                            "，File Name：" + fileEntity.getFileName() +
                            "Size：" + fileEntity.getFileSize() + "Byte");
        } catch (IllegalArgumentException e) {
            // 文件大小超限
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(e.getMessage());
        } catch (Exception e) {
            // 文件读取/保存异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File uploaded failed：" + e.getMessage());
        }
    }

}


