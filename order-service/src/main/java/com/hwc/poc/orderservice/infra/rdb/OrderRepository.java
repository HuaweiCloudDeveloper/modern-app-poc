package com.hwc.poc.orderservice.infra.rdb;

import com.hwc.poc.orderservice.application.contract.OrderRepositoryContract;
import com.hwc.poc.orderservice.application.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.Objects;

@Component
public class OrderRepository implements OrderRepositoryContract {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Order save(Order order) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO orders(uid, name, create_time, total_price, state) VALUES(?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUid());
            ps.setString(2, order.getName());
            ps.setLong(3, order.getCreateTime());
            ps.setBigDecimal(4, order.getTotalPrice());
            ps.setString(5, order.getState().toString());
            return ps;
        }, keyHolder);

        order.setOid(Objects.requireNonNull(keyHolder.getKey()).intValue());

        //sql, order.getUid(), order.getName(), order.getCreateTime(), order.getTotalPrice(), order.getState().toString());

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
