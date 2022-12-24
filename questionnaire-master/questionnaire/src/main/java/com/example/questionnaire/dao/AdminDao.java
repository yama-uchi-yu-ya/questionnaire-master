package com.example.questionnaire.dao;

import com.example.questionnaire.entity.Admin;
import com.example.questionnaire.model.AdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    AdminDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Admin> checkOne(AdminModel adminModel) throws DataAccessException {
        String sql = ""
                + "SELECT"
                + " name,"
                + " password"
                + " FROM"
                + " admins"
                + " WHERE"
                + " name = ?"
                + " AND"
                + " password = ?";
        RowMapper<Admin> rowMapper = new BeanPropertyRowMapper<Admin>(Admin.class);
        List<Admin> adminList = jdbcTemplate.query(sql, rowMapper, adminModel.getName(), adminModel.getPassword());

        if (adminList.size() == 1) {
            System.out.println("抽出成功");
            return adminList;
        }
        System.out.println("抽出失敗");
        return null;
    }
}
