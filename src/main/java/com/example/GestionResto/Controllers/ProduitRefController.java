package com.example.GestionResto.Controllers;
import com.example.GestionResto.entities.Categorie;
import com.example.GestionResto.entities.ProduitRef;
import com.example.GestionResto.repositories.CategorieRepository;
import com.example.GestionResto.repositories.ProduitRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProduitRefController {
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private ProduitRefRepository produitRefRepository;
    List<Categorie> categories= new ArrayList<>();
    @GetMapping("/addProduit")
    public String productForm(Model productModel){
        categories = categorieRepository.findAll();
        productModel.addAttribute("categories", categories);
        ProduitRef produitRef = new ProduitRef();
        productModel.addAttribute("produitRef",produitRef);
        Categorie categorie = new Categorie();
        productModel.addAttribute("categorie",categorie);
        return "produitRef_form";
    }
    @PostMapping("produitRef/saveProduit")
    public String saveProduitStock(@ModelAttribute("produitRef") ProduitRef produitRef,
                                   @ModelAttribute("categorie") Categorie categorie,
                                   @RequestParam("idCategorie") Integer  idCategorie,
                                   Model productModel){
        categorie=categorieRepository.findById(idCategorie).get();
        produitRef.setCategorie(categorie);
        produitRefRepository.save(produitRef);
        return"redirect:/produitRef";
    }
    @GetMapping("/produitRef")
    public String getAllProduit(Model model, @Param("keyword") String keyword) {
        try {
            List<ProduitRef> produitRefs = new ArrayList<>();

            if (keyword == null) {
                produitRefRepository.findAll().forEach(produitRefs::add);

            } else {
                produitRefRepository.findProduitRefByNomProduitRefContainingIgnoreCase(keyword).forEach(produitRefs::add);
                model.addAttribute("keyword", keyword);
            }
            model.addAttribute("categories", categories);
            model.addAttribute("produitRefs",produitRefs);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "produitRef";
    }
    @GetMapping("/produitRef/delete/{id}")
    public String deleteProduit(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            produitRefRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "The product with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/produitRef";

    }
    @GetMapping("/produitRef/{id}")
    public String editProduit(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            categories=categorieRepository.findAll();
            ProduitRef produitRef=produitRefRepository.findById(id).get();
            model.addAttribute("produitRef", produitRef);
            model.addAttribute("categories",categories);
            model.addAttribute("pageNomProduit", "Edit produit (ID: " + id + ")");

            return "produitRef_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/produitRef";
        }}

}