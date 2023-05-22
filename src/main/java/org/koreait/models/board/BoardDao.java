package org.koreait.models.board;

import org.koreait.controllers.RegistForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //게시글 추가
    public boolean insert(RegistForm registForm) {
        String sql = "INSERT INTO BOARD (idx, title, content) VALUES (BOARD_SEQ.NEXTVAL, ?, ?)";
        int cnt = jdbcTemplate.update(sql, registForm.getTitle(), registForm.getContent());
        return cnt > 0;
    }

    //게시글 목록조회
    public List<Board> getList() {
        String sql = "SELECT * FROM BOARD ORDER BY REGDT DESC";
        List<Board> boards = jdbcTemplate.query(sql, this::mapper);
        System.out.println(boards);
        return boards;
    }

    //게시글 상세조회
    public Board getOne(String idx) {
        String sql = "SELECT * FROM BOARD WHERE IDX = ?";
        Board board = jdbcTemplate.queryForObject(sql, this::mapper, idx);

        return board;
    }

    //게시글 삭제
    public boolean delete(String idx) {
        String sql = "DELETE FROM BOARD WHERE IDX = ?";
        int cnt = jdbcTemplate.update(sql, idx);

        return cnt > 0;
    }

    private Board mapper(ResultSet rs, int i) throws SQLException {
        Board board = new Board();
        board.setIdx(rs.getLong("idx"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setRegdt(rs.getTimestamp("regdt").toLocalDateTime());
        return board;
    }

}
