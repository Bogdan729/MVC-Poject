package com.project.controller;

import com.project.dao.ChurchDao;
import com.project.parser.Parser;
import com.project.serialized.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/churches")
@ComponentScan("com.project")
public class ChurchController {
    @Autowired
    private ChurchDao churchDao;

    @PostMapping("/add-to-favorite")
    public String addToFavorite(@RequestParam(value = "id") int favoriteChurchId) {
        if (Serializer.isUnique(favoriteChurchId)) {
            Serializer.saveToFavoriteById(favoriteChurchId);
            return "redirect:/churches/start";
        } else {
            return "warning";
        }
    }

    @PostMapping("/add-to-favorite-for-present-page")
    public String addToFavoritePresentTable(@RequestParam(value = "id") int favoriteChurchId) {
        if (Serializer.isUnique(favoriteChurchId)) {
            Serializer.saveToFavoriteById(favoriteChurchId);
            return "redirect:/churches/show";
        } else {
            return "warning";
        }
    }

    @PostMapping("/remove-from-favorite")
    public String removeToFavorite(@RequestParam(value = "id") int favoriteChurchId) {
        Serializer.removeFromFavorites(favoriteChurchId);
        return "redirect:/churches/favorites";
    }

    @GetMapping("/favorites")
    public String test(Model model) {
        model.addAttribute("churches", Serializer.deserialize());
        return "favorites";
    }

    @GetMapping("/update")
    public String update() {
        Serializer.clearFavorites();
        churchDao.update(Parser.getChurches());
        return "redirect:/churches/start";
    }

    @GetMapping("/show")
    public String showPerson(Model model) {
        model.addAttribute("churches", churchDao.showTable());
        return "present";
    }

    @GetMapping("/start")
    public String start(Model model) {
        if (churchDao.getCount() == 0) {
            model.addAttribute("churches", churchDao.portion());
            return "start";
        }
        if (churchDao.getCount() >= 10) {
            return "redirect:/churches/middle";
        }
        churchDao.plusItems();
        model.addAttribute("churches", churchDao.portion());
        return "start";
    }

    @GetMapping("/middle")
    public String getMiddlePage(Model model) {
        if (churchDao.getCount() == 0) {
            return "redirect:/churches/start";
        }
        if (churchDao.getCount() >= churchDao.getTableSize() - 10) {
            return "redirect:/churches/end";
        }
        model.addAttribute("churches", churchDao.portion());
        return "middle";
    }

    @GetMapping("/end")
    public String getLastPage(Model model) {
        model.addAttribute("churches", churchDao.portion());
        return "end";
    }

    @GetMapping("/plus")
    public String plusItems() {
        churchDao.plusItems();
        return "redirect:/churches/middle";
    }

    @GetMapping("/minus")
    public String minusItems() {
        churchDao.minusItems();
        return "redirect:/churches/middle";
    }

    @GetMapping("/download")
    @ResponseBody
    public FileSystemResource download() {
        return new FileSystemResource("D:/ProjectSprigBoot/springBootApp/src/main/resources/churches.json");
    }
}