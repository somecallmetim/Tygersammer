package com.tygershammer.tygersammer.controllers;

import com.tygershammer.tygersammer.models.Hashtag;
import com.tygershammer.tygersammer.models.Unit;
import com.tygershammer.tygersammer.models.UnitReview;
import com.tygershammer.tygersammer.repos.HashtagRepoInterface;
import com.tygershammer.tygersammer.repos.UnitRepoInterface;
import com.tygershammer.tygersammer.repos.UnitReviewRepoInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/unit")
@Controller
public class UnitController {

    private UnitRepoInterface unitRepoInterface;
    private UnitReviewRepoInterface unitReviewRepoInterface;
    private HashtagRepoInterface hashtagRepoInterface;

    public UnitController(UnitRepoInterface unitRepoInterface, UnitReviewRepoInterface unitReviewRepoInterface,
                          HashtagRepoInterface hashtagRepoInterface){
        this.unitRepoInterface = unitRepoInterface;
        this.unitReviewRepoInterface = unitReviewRepoInterface;
        this.hashtagRepoInterface = hashtagRepoInterface;
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
        return "redirect:/unit/displayAllUnits";
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
        return "redirect:/unit/" + name;
    }

    @GetMapping("/deleteReview/{reviewId}/{unitName}")
    public String deleteReview(@PathVariable("reviewId") long reviewId,
                               @PathVariable("unitName") String unitName, Model model){

        // unitReviewRepoInterface returns an Optional<UnitReview>. The 'get' function for that class will get us the
            // actual UnitReview object we want
        UnitReview unitReview = unitReviewRepoInterface.findById(reviewId).get();
        unitReviewRepoInterface.delete(unitReview);

        Unit unit = unitRepoInterface.findByName(unitName);

        model.addAttribute(unit);
        return "redirect:/unit/" + unitName;
    }

    @PostMapping("/{name}/addHashtag")
    public String addHashtag(@PathVariable String name, @RequestParam String hashtag, ModelMap modelMap){
        Unit unit = unitRepoInterface.findByName(name);
        Hashtag hashtagObject = hashtagRepoInterface.findByHashtag(hashtag).orElseGet(() -> new Hashtag(hashtag, unit));
        hashtagRepoInterface.save(hashtagObject);
        unit.addHashtag(hashtagObject);
        unitRepoInterface.save(unit);
        modelMap.addAttribute(unit);
        return "redirect:/unit/" + name;
    }
}
