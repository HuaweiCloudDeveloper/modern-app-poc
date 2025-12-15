package com.hwc.poc.orderservice.infra.rdb;

import com.hwc.poc.orderservice.application.contract.OrderRepositoryContract;
import com.hwc.poc.orderservice.application.model.Order;
import com.hwc.poc.orderservice.application.model.OrderStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;

@Component
public class OrderRepository implements OrderRepositoryContract {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
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
        return order;
    }

    @Override
    public Order load(Integer orderId) {
        String sql = "SELECT  oid, uid, name, create_time, total_price, state from orders where oid = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{orderId}, (rs, rowNum) -> {
            Order order = new Order();
            order.setOid(rs.getInt("oid"));
            order.setName(rs.getString("name"));
            order.setCreateTime(rs.getLong("create_time"));
            order.setTotalPrice(rs.getBigDecimal("total_price"));
            order.setState(OrderStates.valueOf(rs.getString("state")));
            order.setUid(rs.getInt("uid"));
            return order;
        });
    }


}
