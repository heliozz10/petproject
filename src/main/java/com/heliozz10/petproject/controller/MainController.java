package com.heliozz10.petproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("contextPath", request.getContextPath());
        return "index.html";
    }
}
