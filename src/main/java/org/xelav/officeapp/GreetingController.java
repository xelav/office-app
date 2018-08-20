package org.xelav.officeapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xelav.officeapp.dao.OfficeDao;
import org.xelav.officeapp.model.Office;

import java.util.List;


@Controller
public class GreetingController {

    @Autowired
    private OfficeDao dao;

    @GetMapping("/home")
    public String greeting(Model model) {

        List<Office> offices = dao.findAll( new Sort(Sort.Direction.ASC, "id") );;
        model.addAttribute("offices", offices);

        return "greeting";
    }

}