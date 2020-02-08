/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.auth.web;

import com.company.auth.model.Location;
import com.company.auth.service.AtmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring REST endpoint
 */
@Controller
public class CityController {

    @Autowired
    AtmService atmService;

    @GetMapping("/search")
    public String searchCity(Model model) {
        if (atmService != null) {
            model.addAttribute("locations", atmService.findAllAtms());
        }
        return "searchPage";
    }

}
