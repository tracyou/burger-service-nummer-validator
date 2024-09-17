package com.example.bsnvalidator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;

@Controller
@RequestMapping("")
public class BsnValidatorController {

    private final BsnValidator bsnValidator;

    public BsnValidatorController(BsnValidator bsnValidator) {
        this.bsnValidator = bsnValidator;
    }


    @GetMapping("/")
    public String getIndex(Model model) {

        return "index";
    }

    @PostMapping("/sent")
    public String submit(@RequestParam("input") String bsn, Model model) {
        model.addAttribute("bsn", bsn);

//        if (!bsnValidator.isValid(bsn)) {
//            try {
//                bsnValidator.hasRightLength(bsn);
//                bsnValidator.elevenProofCheck(bsn);
//                bsnValidator.hasNoOrders(bsn);
//            } catch (IllegalArgumentException e) {
//                model.addAttribute("error", e.getMessage());
//                return "index";
//            }
//        } else {
//            model.addAttribute("isValid", true);
//        }

        try {
            if (!bsnValidator.isValid(bsn)) {
                try {
                    bsnValidator.hasRightLength(bsn);
                } catch (IllegalArgumentException e) {
                    model.addAttribute("error", e.getMessage());
                }

                try {
                    bsnValidator.elevenProofCheck(bsn);
                } catch (IllegalArgumentException e) {
                    model.addAttribute("error", e.getMessage());
                }

                try {
                    bsnValidator.hasNoOrders(bsn);
                } catch (IllegalArgumentException e) {
                    model.addAttribute("error", e.getMessage());
                }
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "index";
    }

}
