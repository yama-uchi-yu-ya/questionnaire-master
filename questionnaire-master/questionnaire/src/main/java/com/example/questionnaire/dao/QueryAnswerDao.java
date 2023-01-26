package com.example.questionnaire.dao;

import com.example.questionnaire.entity.Meat;
import com.example.questionnaire.entity.Vegetable;
import com.example.questionnaire.model.QuestionAnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class QueryAnswerDao {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    QueryAnswerDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
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

    public void add(QuestionAnswerModel questionAnswerModel) {
        // answerテーブルにインサート ?のところは後から補完する
        jdbcTemplate.update("INSERT INTO answers (meat_id, idol_name, is_deleted, create_date, update_date) VALUES (?, ?, ?, ?, ?)",
                questionAnswerModel.getLike_meat(),
                questionAnswerModel.getLike_idol(),
                false,
                // 現在時刻取得
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        // answersテーブルから最新のレコードのanswer_idを取得
        Integer answer_id = jdbcTemplate.queryForObject("SELECT answer_id FROM answers ORDER BY answer_id DESC LIMIT 1", Integer.class);
        // questionAnswerModelから好きな野菜リストを取得
        List like_veg_list = questionAnswerModel.getLike_veg();
        // 好きな野菜リストの中身をforEachで順に実行
        like_veg_list.forEach(vegetable_id -> {
            // answer_vegetablesテーブルにインサート ?のところは後から補完する
            jdbcTemplate.update("INSERT INTO answer_vegetables (vegetable_id, answer_id, is_deleted, create_date, update_date) VALUES (?, ?, ?, ?, ?)",
                    vegetable_id,
                    // さっきanswersテーブルにインサートしたレコードのanswer_id
                    answer_id,
                    false,
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
        });
    }
}