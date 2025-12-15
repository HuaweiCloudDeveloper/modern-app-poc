package com.hwc.poc.orderservice.infra.rdb;

import com.hwc.poc.orderservice.application.contract.OrderRepositoryContract;
import com.hwc.poc.orderservice.application.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderRepository implements OrderRepositoryContract {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Order save(Order order) {

        String sql = "INSERT INTO orders(uid, create_time, total_price, state) VALUES(?, ?, ?, ?)";

        jdbcTemplate.update(sql, order.getUid(), order.getCreateTime(), order.getTotalPrice(), order.getState().toString());

        return order;
    }

    @Override
    public Order load(Integer orderId) {
//        Optional<Order> orderDataEntityOpt = orderJpaPersistence.findById(orderId);
//
//        if (orderDataEntityOpt.isPresent()) {
//            List<OrderItem> orderItemDataEntities = orderItemJpaPersistence.findByOid(orderId);
//            Order result = orderDataEntityOpt.get();
//            result.setItems(orderItemDataEntities);
//
//            Payment payment = paymentJpaPersistence.findByOid(orderId);
//            if (Objects.nonNull(payment)) {
//                result.setPayment(payment);
//            }
//
//            return result;
//        }

        return null;
    }


}
