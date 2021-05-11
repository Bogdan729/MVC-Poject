package com.project.controller;

import com.project.dao.ChurchDao;
import com.project.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/churches")
@ComponentScan("com.project")
public class ChurchController {
    @Autowired
    private ChurchDao churchDao;

    @GetMapping("/update")
    public String update() {
        churchDao.update(Parser.getChurches());
        return "redirect:/churches/first";
    }

    @GetMapping("/show")
    public String showPerson(Model model) {
        model.addAttribute("churches", churchDao.showTable());
        return "present";
    }

    @GetMapping("/first")
    public String test(Model model) {
        if (churchDao.getCount() == 0) {
            model.addAttribute("churches", churchDao.portion());
            return "first";
        }
        if (churchDao.getCount() >= 10) {
            return "redirect:/churches/second";
        }
        churchDao.plusItems();
        model.addAttribute("churches", churchDao.portion());
        return "first";
    }

    @GetMapping("/second")
    public String testMinus(Model model) {
        if (churchDao.getCount() == 0) {
            return "redirect:/churches/first";
        }
        if (churchDao.getCount() >= churchDao.getTableSize() - 10) {
            return "redirect:/churches/third";
        }
        model.addAttribute("churches", churchDao.portion());
        return "second";
    }

    @GetMapping("/third")
    public String thirdPage(Model model) {
        model.addAttribute("churches", churchDao.portion());
        return "third";
    }

    @GetMapping("/plus")
    public String plusItems() {
        churchDao.plusItems();
        return "redirect:/churches/second";
    }

    @GetMapping("/minus")
    public String minusItems() {
        churchDao.minusItems();
        return "redirect:/churches/second";
    }

    @GetMapping("/download")
    @ResponseBody
    public FileSystemResource download() {
        return new FileSystemResource("D:/ProjectSprigBoot/springBootApp/src/main/resources/churches.json");
    }

}