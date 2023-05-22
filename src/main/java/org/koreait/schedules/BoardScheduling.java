package org.koreait.schedules;

import org.koreait.models.board.Board;
import org.koreait.models.board.Board_S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class BoardScheduling {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "0 0 1 * * *")
    //@Scheduled(cron = "*/5 * * * * *")
    public void process() {
        String sql = "SELECT COUNT(*), TO_CHAR(REGDT, 'YYYY/MM/DD HH24') " +
                "FROM BOARD WHERE REGDT " +
                "BETWEEN SYSDATE - 1 AND SYSDATE " +
                "GROUP BY TO_CHAR(REGDT, 'YYYY/MM/DD HH24')";
        List<Board_S> boards_s = jdbcTemplate.query(sql, this::mapper);
        System.out.println(boards_s);
    }

    private Board_S mapper(ResultSet rs, int i) throws SQLException {
        Board_S boardS = new Board_S();
        boardS.setCnt(rs.getLong("COUNT(*)"));
        boardS.setEachdate(rs.getString("TO_CHAR(REGDT,'YYYY/MM/DD HH24')"));
        return boardS;
    }
}
