package com.project.controller;

import com.project.dao.ChurchDao;
import com.project.dao.ChurchDb;
import com.project.prserJson.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/churches")
@ComponentScan("com.project")
public class ChurchController {

    private final ChurchDb churchDB;
    @Autowired private ChurchDao churchDao;

    @Autowired
    ChurchController(ChurchDb churchDB) {
        this.churchDB = churchDB;
    }

    @GetMapping("/general")
    public String general() {
        return "general";
    }

    @GetMapping("/clear")
    public String clear() {
        churchDB.clearTable();
        return "redirect:/churches/general";
    }

    @GetMapping("/update")
    public String add() {
        churchDB.update(Parser.getChurches());
        return "redirect:/churches/general";
    }

    @GetMapping("/show")
    public String showPerson(Model model) {
        model.addAttribute("churches" , churchDao.show());
        return "present";
    }
}