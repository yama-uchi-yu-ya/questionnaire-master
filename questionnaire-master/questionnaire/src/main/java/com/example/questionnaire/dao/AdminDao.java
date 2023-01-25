package com.example.questionnaire.dao;

import com.example.questionnaire.entity.*;
import com.example.questionnaire.model.AdminModel;
import com.example.questionnaire.model.UpdateAnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
                + " answer_vegetables"
                + " WHERE "
                + " is_deleted = 0";
        RowMapper<ViewVegetableAnswer> rowMapper = new BeanPropertyRowMapper<ViewVegetableAnswer>(ViewVegetableAnswer.class);
        List<ViewVegetableAnswer> viewVegetableAnswerList = jdbcTemplate.query(sql, rowMapper);

        return viewVegetableAnswerList;
    }

    public void delete(int answer_id) {
        System.out.println("回答番号" + answer_id + "をdeleteしたよ");
        jdbcTemplate.update("UPDATE answers SET is_deleted = '1' WHERE answer_id = ?", answer_id);
        jdbcTemplate.update("UPDATE answer_vegetables SET is_deleted = '1' WHERE answer_id = ?", answer_id);

    }

    public List<UpdateAnswer> updateAnswerList(int answer_id) {
        String sql = ""
                + "SELECT"
                + " answer_id,"
                + " meat_id,"
                + " idol_name"
                + " FROM"
                + " answers"
                + " WHERE"
                + " answer_id = ?";
        RowMapper<UpdateAnswer> rowMapper = new BeanPropertyRowMapper<UpdateAnswer>(UpdateAnswer.class);
        List<UpdateAnswer> updateAnswerList = jdbcTemplate.query(sql, rowMapper, answer_id);

        return updateAnswerList;
    }

    public List<Integer> updateVegetableAnswerList(int answer_id) {
        String sql = ""
                + "SELECT"
                + " vegetable_id"
                + " FROM"
                + " answer_vegetables"
                + " WHERE"
                + " answer_id = ?"
                + " AND "
                + " is_deleted = 0";
        RowMapper<UpdateVegetableAnswer> rowMapper = new BeanPropertyRowMapper<UpdateVegetableAnswer>(UpdateVegetableAnswer.class);
        List<UpdateVegetableAnswer> updateVegetableAnswerList = jdbcTemplate.query(sql, rowMapper, answer_id);

        List<Integer> answerVegetableIdList = new ArrayList<>();
        updateVegetableAnswerList.forEach(updateVegetableAnswer -> {
            answerVegetableIdList.add(updateVegetableAnswer.getVegetable_id());
        });

        return answerVegetableIdList;
    }

    public void update(UpdateAnswerModel updateAnswerModel) {
        jdbcTemplate.update("UPDATE answers SET meat_id = ?, idol_name = ?, update_date = ? WHERE answer_id = ?",
                updateAnswerModel.getMeat_id(),
                updateAnswerModel.getIdol_name(),
                new Timestamp(System.currentTimeMillis()),
                updateAnswerModel.getAnswer_id()
                );

        jdbcTemplate.update("UPDATE answer_vegetables SET is_deleted = '1' WHERE answer_id = ?", updateAnswerModel.getAnswer_id());

        List like_veg_list = updateAnswerModel.getVegetable_id();
        like_veg_list.forEach(vegetable_id -> {
            jdbcTemplate.update("INSERT INTO answer_vegetables (vegetable_id, answer_id, is_deleted, create_date, update_date) VALUES (?, ?, ?, ?, ?)",
                    vegetable_id,
                    updateAnswerModel.getAnswer_id(),
                    false,
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
        });
    }
}
