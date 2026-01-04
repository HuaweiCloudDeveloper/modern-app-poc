package com.hwc.poc.orderservice.resource;


import com.hwc.poc.orderservice.application.OrderService;
import com.hwc.poc.orderservice.application.model.Order;
import com.hwc.poc.orderservice.resource.parameters.OrderCreationRequest;
import com.hwc.poc.orderservice.resource.parameters.OrderCreationResponse;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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

    @RequestMapping(value = "/{oid}", method = RequestMethod.GET, headers = "Accept=application/json")
    public OrderCreationResponse getCurrentOrder(@PathVariable("oid") Integer orderId) {
        final Order order = orderService.getOrderById(orderId);
        return mapper.map(order, OrderCreationResponse.class);
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.POST, headers = "Accept=application/json")
    public String createInventory(OrderCreationResponse request) {

        Order order = mapper.map(request, Order.class);
        orderService.lockInventory(order);
        return null;
    }


    @RequestMapping(value = "/inventory/{oid}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String queryInventory(@PathVariable("oid") Integer oid) {

//        String orderDetails = inventoryService.queryInventory(oid);
//
//        if(StringUtils.isEmpty(orderDetails)){
//            return new ResultTemplate<String>(false);
//        }
//        return new ResultTemplate<String>(true, orderDetails);
        return null;

    }

    @RequestMapping(value = "/inventory/{oid}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public String deleteInventory(@PathVariable("oid") Integer oid) {

//        inventoryService.deleteInventory(oid);
//        return new ResultTemplate<Boolean>(true);
        return null;

    }

    @RequestMapping(value = "/inventory/notify", method = RequestMethod.POST, headers = "Accept=application/json")
    public String notifyInventory(OrderCreationResponse request) {

//        Order order = mapper.map(request, Order.class);
//        inventoryService.notifyInventory(order);
//        return new ResultTemplate<Boolean>(true);
        return null;
    }

}


