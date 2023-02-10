package com.example.GestionResto.Controllers;
import com.example.GestionResto.entities.ProduitRef;
import com.example.GestionResto.entities.ProduitStock;
import com.example.GestionResto.entities.Statue;
import com.example.GestionResto.repositories.ProduitRefRepository;
import com.example.GestionResto.repositories.ProduitStockRepository;
import com.example.GestionResto.repositories.StatueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class ProduitStockController {
    @Autowired
    private ProduitStockRepository produitStockRepository;
    @Autowired
    private ProduitRefRepository produitRefRepository;

    List<ProduitRef> produitRefs = new ArrayList<>();
    List<Statue> statues = new ArrayList<>();
    @Autowired
    private StatueRepository statueRepository;

    @GetMapping("/addProduitStock")
    public String addProduitStock(Model produitStockModel,
                                  @ModelAttribute("produitStock") ProduitStock produitStock,
                                  @ModelAttribute("produitRef") ProduitRef produitRef) {
        statues = statueRepository.findAll();
        produitRefs = produitRefRepository.findAll();
        Calendar cal = Calendar.getInstance();
        produitStock.setDateTime(new Date());
        cal.setTime(produitStock.getDateTime());
        cal.add(Calendar.DATE, (int) produitRef.getDureeProduitRef());
        produitStock.setDateOver(cal.getTime());
        produitStockModel.addAttribute("produitRefs", produitRefs);
        produitStockModel.addAttribute("statues", statues);
        produitStockModel.addAttribute("produitStock", produitStock);
        produitStockModel.addAttribute("produitRef", produitRef);
        Statue statue = new Statue();
        produitStockModel.addAttribute("statue", statue);
        return "produitStock_form";
    }


    @PostMapping("/produitStock/saveProduitStock")
    public String saveProduitStock(@ModelAttribute("produitStock") ProduitStock produitStock,
                                   @ModelAttribute("produitRef") ProduitRef produitRef,
                                   @ModelAttribute("statue") Statue statue,
                                   @RequestParam("idProduitRef") Integer idProduitRef,
                                   @RequestParam("idStatue") Integer idStatue,
                                   Model produitStockModel) {
        produitRef = produitRefRepository.findById(idProduitRef).get();
        statue = statueRepository.findById(idStatue).get();
        produitStock.setProduitRef(produitRef);
        produitStock.setStatue(statue);
        Calendar cal = Calendar.getInstance();
        produitStock.setDateTime(new Date());
        cal.setTime(produitStock.getDateTime());
        cal.add(Calendar.DATE, (int) produitRef.getDureeProduitRef());
        produitStock.setDateOver(cal.getTime());
        produitStockRepository.saveAndFlush(produitStock);
        produitStockModel.addAttribute("produitRef", produitRef);
        produitStockModel.addAttribute("statue", statue);
        produitStockModel.addAttribute("produitStock", produitStock);

        return "redirect:/produitStock";
    }

    @GetMapping("/produitStock")
    public String getAllProduit(Model model, @Param("keyword") String keyword) {
        try {
            List<ProduitStock> produitStocks = new ArrayList<>();

            if (keyword == null) {
                produitStockRepository.findAll().forEach(produitStocks::add);

            } else {
                produitStockRepository.findByProduitRefNomProduitRefLikeIgnoreCase("%" + keyword + "%").forEach(produitStocks::add);
                model.addAttribute("keyword", keyword);
            }
            model.addAttribute("produitStocks", produitStocks);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "produitStock";
    }

    @Scheduled(fixedRate = 60000)
    @GetMapping("/produitStock/{id}")
    public String editProduitStock(@PathVariable("id") Integer idStock,RedirectAttributes redirectAttributes) {
        try {
            List<ProduitStock> produitStocks = produitStockRepository.findAll();
            for (ProduitStock produitStock : produitStocks) {
                Statue statue = null;
                if (produitStock.getQuantite() == 0) {
                    statue = statueRepository.findByNomStatue("out stock");
                    if (statue == null) {
                        statue = new Statue(154, "out stock");
                        statueRepository.save(statue);
                    }
                } else if (produitStock.getDateOver().before(new Date())) {
                    statue = statueRepository.findByNomStatue("expired");
                    if (statue == null) {
                        statue = new Statue(154, "expired");
                        statueRepository.save(statue);
                    }
                } else {
                    statue = statueRepository.findByNomStatue("in stock");
                    if (statue == null) {
                        statue = new Statue(154, "in stock");
                        statueRepository.save(statue);
                    }
                }
                produitStock.setStatue(statue);
                produitStockRepository.save(produitStock);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/produitStock";
    }

    @GetMapping("/produitStock/delete/{id}")
    public String deleteProduitStock(@PathVariable("id") Integer id,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {
        try {
            produitStockRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "The product with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/produitStock";
    }

}