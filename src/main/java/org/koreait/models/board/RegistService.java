package org.koreait.models.board;

import org.koreait.controllers.RegistForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistService {

    @Autowired
    private RegistValidator registValidator;

    @Autowired
    private BoardDao boardDao;

    public void regist(Board board) {
        registValidator.check(board);
    }

    public void insert(RegistForm registForm) {
        boardDao.insert(registForm);
    }
}
