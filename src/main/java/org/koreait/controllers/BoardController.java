package org.koreait.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.koreait.models.board.Board;
import org.koreait.models.board.BoardDao;
import org.koreait.models.board.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private RegistService registService;

    @Autowired
    private BoardDao boardDao;

    @GetMapping("/regist")
    public String regist(@ModelAttribute RegistForm registForm) {

        return "board/regist";
    }

    @PostMapping("/regist")
    public String regist(@Valid RegistForm registForm, Errors errors) {

        if (errors.hasErrors()) {
            return "board/regist";
        }

        registService.insert(registForm);

        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String list(Model model) {

        List<Board> boards = boardDao.getList();
        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {

        Board board = boardDao.getOne(request.getParameter("idx"));
        model.addAttribute("board", board);

        return "board/detail";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, Model model) {

        //삭제 후
        boardDao.delete(request.getParameter("idx"));
        System.out.println(request.getParameter("idx"));

        //리스트 담기
        List<Board> boards = boardDao.getList();
        model.addAttribute("boards", boards);

        return "redirect:/board/list";
    }
}
