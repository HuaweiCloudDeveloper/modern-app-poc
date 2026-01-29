package com.hwc.poc.orderservice.infra.rdb;

import com.hwc.poc.orderservice.application.contract.FileRepositoryContract;
import com.hwc.poc.orderservice.application.contract.OrderRepositoryContract;
import com.hwc.poc.orderservice.application.model.File;
import com.hwc.poc.orderservice.application.model.Order;
import com.hwc.poc.orderservice.application.model.OrderStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Component
public class FileRepository implements FileRepositoryContract {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public File upload(MultipartFile file)  throws Exception{

        String sql = "INSERT INTO files (file_name, file_size, file_content, upload_time) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, file.getOriginalFilename());
            ps.setLong(2, file.getSize());
            try {
                ps.setBytes(3, file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ps.setTimestamp(4, new Timestamp(new Date().getTime()));
            return ps;
        }, keyHolder);

        File fileEntity = new File();
        fileEntity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileSize(file.getSize());
        fileEntity.setFileContent(file.getBytes());
        fileEntity.setUploadTime(new Date());

        return fileEntity;
    }
}
