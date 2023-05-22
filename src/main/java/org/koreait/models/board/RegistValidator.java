package org.koreait.models.board;

import org.koreait.validators.Validator;
import org.springframework.stereotype.Component;

@Component
public class RegistValidator implements Validator<Board> {

    @Override
    public void check(Board board) {
        requiredCheck(board.getTitle(), new RegistValidationException("제목을 입력하세요."));
        requiredCheck(board.getContent(), new RegistValidationException("내용을 입력하세요."));

        // 제목은 VARCHAR2(50)이라 자리 수 제한 있음
        lengthCheck(board.getTitle(), 25, new RegistValidationException("제목은 25글자 이하로 입력하세요."));

        // 내용은 CLOB이라 자리 수 제한 없음
    }
}
