package com.example.questionnaire.dao;

import com.example.questionnaire.entity.*;
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
    public List<Meat> meatList() {
        String sql = ""
                + "SELECT"
                + " meat_id,"
                + " name"
                + " FROM"
                + " meats";
        RowMapper<Meat> rowMapper = new BeanPropertyRowMapper<Meat>(Meat.class);
        List<Meat> meatList = jdbcTemplate.query(sql, rowMapper);

        return meatList;
    }

    public List<Vegetable> vegetableList() {
        String sql = ""
                + "SELECT"
                + " vegetable_id,"
                + " name"
                + " FROM"
                + " vegetables";
        RowMapper<Vegetable> rowMapper = new BeanPropertyRowMapper<Vegetable>(Vegetable.class);
        List<Vegetable> vegetableList = jdbcTemplate.query(sql, rowMapper);

        return vegetableList;
    }

    public List<ViewVegetableAnswer> viewVegetableAnswerList() {
        String sql = ""
                + "SELECT"
                + " vegetable_id,"
                + " answer_id"
                + " FROM"
                + " answer_vegetables";
        RowMapper<ViewVegetableAnswer> rowMapper = new BeanPropertyRowMapper<ViewVegetableAnswer>(ViewVegetableAnswer.class);
        List<ViewVegetableAnswer> viewVegetableAnswerList = jdbcTemplate.query(sql, rowMapper);

        return viewVegetableAnswerList;
    }

    public void delete(int answer_id) {
        System.out.println("deleteしてるよ");
        System.out.println(answer_id);
        jdbcTemplate.update("UPDATE answers SET is_deleted = '1' WHERE answer_id = ?", answer_id);
        jdbcTemplate.update("UPDATE answer_vegetables SET is_deleted = '1' WHERE answer_id = ?", answer_id);

    }
}
