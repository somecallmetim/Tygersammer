package com.tygershammer.tygersammer.controllers;

import com.tygershammer.tygersammer.models.Unit;
import com.tygershammer.tygersammer.repos.UnitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/unit")
@Controller
public class UnitController {

    private UnitRepository unitRepository;

    public UnitController(UnitRepository unitRepository){
        this.unitRepository = unitRepository;
    }

    @GetMapping("/new")
    public String createNewUnit(){
        return "createNewUnit";
    }

    @PostMapping("/new")
    public String createNewUnit(@RequestParam String name){
        Unit unit = new Unit(name);
        unitRepository.save(unit);
        System.out.println(unit.getName());
        return "home";
    }

}
