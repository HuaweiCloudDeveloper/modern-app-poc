package com.hwc.poc.inventoryservice.resource;


import com.hwc.poc.inventoryservice.application.InventoryService;
import com.hwc.poc.inventoryservice.application.model.Order;
import com.hwc.poc.inventoryservice.application.utils.ResultTemplate;
import com.hwc.poc.inventoryservice.resource.parameters.OrderEntityRequest;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/inventory")
public class InventoryResource {

    protected static final ModelMapper mapper = new ModelMapper();

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    public InventoryResource(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResultTemplate<Integer> createInventory(@RequestBody OrderEntityRequest request) {

        Order order = mapper.map(request, Order.class);
        Integer oid = inventoryService.createInventory(order);
        return new ResultTemplate<Integer>(true, oid);
    }

    @RequestMapping(value = "/{oid}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResultTemplate<String> queryInventory(@PathVariable("oid") Integer oid) {

        String orderDetails = inventoryService.queryInventory(oid);

        if(StringUtils.isEmpty(orderDetails)){
            return new ResultTemplate<String>(false);
        }
        return new ResultTemplate<String>(true, orderDetails);
    }

    @RequestMapping(value = "/{oid}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResultTemplate<Boolean> deleteInventory(@PathVariable("oid") Integer oid) {

        inventoryService.deleteInventory(oid);
        return new ResultTemplate<Boolean>(true);
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResultTemplate<Boolean> notifyInventory(@RequestBody OrderEntityRequest request) {

        Order order = mapper.map(request, Order.class);
        inventoryService.notifyInventory(order);
        return new ResultTemplate<Boolean>(true);
    }

}


