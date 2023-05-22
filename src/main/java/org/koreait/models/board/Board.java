package org.koreait.models.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private Long idx;
    private String title;
    private String content;
    private LocalDateTime regdt;
}
