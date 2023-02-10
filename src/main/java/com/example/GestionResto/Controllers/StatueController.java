package com.example.GestionResto.Controllers;
import com.example.GestionResto.entities.Statue;
import com.example.GestionResto.repositories.StatueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
@Controller
public class StatueController {
    @Autowired
    private StatueRepository statueRepository;
    @GetMapping("/statue")
    public String getAllStatues(Model model, @Param("keyword") String keyword) {
        try {
            List<Statue>statues= new ArrayList<>();

            if (keyword == null) {
                statueRepository.findAll().forEach(statues::add);

            } else {
                statueRepository.findByNomStatueContainingIgnoreCase(keyword).forEach(statues::add);
                model.addAttribute("keyword", keyword);
            }
            model.addAttribute("statues", statues);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "statue";
    }

    @GetMapping("/statue/new")
    public String addStatue(Model model) {
        Statue  statue   = new Statue();

        model.addAttribute("statue", statue);
        model.addAttribute("pageTitle", "Create new statue");

        return "statue_form";
    }

    @PostMapping("/statue/save")
    public String saveStatues(Statue statue , RedirectAttributes redirectAttributes) {
        try {
            statueRepository.save(statue);

            redirectAttributes.addFlashAttribute("message", "The statue has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/statue";
    }


    @GetMapping("/statue/{id}")
    public String editStatues(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Statue  statue  = statueRepository.findById(id).get();

            model.addAttribute("statue", statue);
            model.addAttribute("pageNomStatue", "Edit Statue (ID: " + id + ")");

            return "statue_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/statue";
        }}

    @GetMapping("/statue/delete/{id}")
    public String deleteCategorie(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            statueRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "The Statue with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/statue";
    }



}