package org.koreait.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.models.board.Board;
import org.koreait.models.board.BoardDao;
import org.koreait.models.board.RegistService;
import org.koreait.models.board.RegistValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("게시글 등록 테스트")
public class BoardRegistTest {
    @Autowired
    private RegistService registService;

    @Autowired
    private BoardDao boardDao;

    private Board getBoard() {
        Board board = new Board();
        board.setTitle("제목입니다");
        board.setContent("내용입니다");
        return board;
    }

    @Test
    @DisplayName("게시글 등록에 성공하면 예외가 발생하지 않음")
    void registSuccessTest() {
        assertDoesNotThrow(() -> {
            Board board = getBoard();
            registService.regist(board);
        });
    }

    @Test
    @DisplayName("필수 항목 검증 (제목, 내용)")
    void requiredFieldsCheckTest() {
        assertAll(
                //제목 - null
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = getBoard();
                    board.setTitle(null);
                    registService.regist(board);
                }),
                //제목 - 빈 값
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = getBoard();
                    board.setTitle("    ");
                    registService.regist(board);
                }),
                //내용 - null
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = getBoard();
                    board.setContent(null);
                    registService.regist(board);
                }),
                //내용 - 빈 값
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = getBoard();
                    board.setContent("    ");
                    registService.regist(board);
                })
        );
    }

    @Test
    @DisplayName("제목 글자 수 체크 (VARCHAR2(50)) - 영어, 한글 통합해서 25글자로 제한")
    void lengthCheckTest() {
        assertAll(
                //한글 - 25글자 초과
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = getBoard();
                    board.setTitle("가나다라마가나다라마가나다라마가나다라마가나다라마가"); //한글 26글자
                    registService.regist(board);
                }),
                //영어 - 25글자 초과
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = getBoard();
                    board.setTitle("abcdeabcdeabcdeabcdeabcdea"); //영어 26글자
                    registService.regist(board);
                })
        );
    }
}
