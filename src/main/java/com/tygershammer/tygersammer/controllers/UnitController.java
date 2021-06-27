package com.tygershammer.tygersammer.controllers;

import com.tygershammer.tygersammer.models.Unit;
import com.tygershammer.tygersammer.models.UnitReview;
import com.tygershammer.tygersammer.repos.UnitRepoInterface;
import com.tygershammer.tygersammer.repos.UnitReviewRepoInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/unit")
@Controller
public class UnitController {

    private UnitRepoInterface unitRepoInterface;
    private UnitReviewRepoInterface unitReviewRepoInterface;

    public UnitController(UnitRepoInterface unitRepoInterface, UnitReviewRepoInterface unitReviewRepoInterface){
        this.unitRepoInterface = unitRepoInterface;
        this.unitReviewRepoInterface = unitReviewRepoInterface;
    }

    @GetMapping("/new")
    public String createNewUnit(){
        return "createNewUnit";
    }

    @PostMapping("/new")
    public String createNewUnit(@RequestParam String name){
        Unit unit = new Unit(name);
        unitRepoInterface.save(unit);
        System.out.println(unit.getName());
        return "home";
    }

    @GetMapping("/displayAllUnits")
    public String displayAllUnits(Model model){
        Iterable<Unit> allUnits = unitRepoInterface.findAll();
        model.addAttribute("units", allUnits);
        return "displayAllUnits";
    }

    @GetMapping("/{name}")
    public String getUnitByName(Model model, @PathVariable String name){
        Unit unit = unitRepoInterface.findByName(name);
        model.addAttribute(unit);
        return "getUnitByName";
    }

    @PostMapping("/{name}/addReview")
    public String addReview(@PathVariable String name, @RequestParam String review, ModelMap modelMap){
        Unit unit = unitRepoInterface.findByName(name);
        UnitReview unitReview = new UnitReview(review, unit);
        unitReviewRepoInterface.save(unitReview);
        modelMap.addAttribute(unit);
        return "getUnitByName";
    }
}
