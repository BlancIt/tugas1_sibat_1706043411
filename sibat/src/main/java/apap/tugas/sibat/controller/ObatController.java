package apap.tugas.sibat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.sibat.model.*;
import apap.tugas.sibat.service.*;

@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @RequestMapping(value = "/", method = RequestMethod.GET) 
    public String home(@RequestParam(value = "nomorRegistrasi", required = false) Integer nomorRegistrasi, Model model) {
    	List<ObatModel> obatList = obatService.getObatList();
    	model.addAttribute("obatList", obatList);
        return "home";
    }
}

    /*// URL mapping yang digunakan untuk mengakses halaman add obat
    @RequestMapping(value = "/obat/tambah", method = RequestMethod.GET)
    public String addObatFormPage(Model model) {
        ObatModel obat = new ObatModel();
        List<SupplierModel> penyedia = obat.getListSupplier();
        
        
        
        model.addAttribute("obat", obat);
        return "form-add-obat";
    }
    
    @RequestMapping(value="/obat/tambah}", params={"addRow"}, method = RequestMethod.POST)
    public String addRow(SupplierModel supplier, ObatModel obat, BindingResult bindingResult, Model model) {
        if (obat.getListSupplier() == null) {
            restoran.setListMenu(new ArrayList<MenuModel>());
        }
        restoran.getListMenu().add(menu);
        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    }

    //URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add obat
    @RequestMapping(value = "/obat/add", method = RequestMethod.POST)
    public String addObatSubmit(@ModelAttribute ObatModel obat, Model model) {
    	obat.generateKode();
        obatService.addObat(obat);
        /*model.addAttribute("kodeObat", obat.getKode());
        model.addAttribute("penerbitObat", obat.getPenerbit());
        model.addAttribute("pengarangObat", obat.getPengarang());
        model.addAttribute("judulObat", obat.getJudul());
        model.addAttribute("jenisObat", obat.getJenis());
        model.addAttribute("tanggalTerbitObat", obat.getTanggalTerbit());
        model.addAttribute("tahun", obat.getTanggalTerbitInYear());
        model.addAttribute("abstrakObat", obat.getAbstrak());
        model.addAttribute("statusObat", obat.getStatus());
        return "add-obat";
    }
    
    @RequestMapping(value = "/obat/tambah}", method = RequestMethod.GET)
    public String addProductFormPage(@PathVariable(value="idRestoran") Long idRestoran, Model model) {
        ArrayList<MenuModel> menu = new ArrayList<>();
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        restoran.setListMenu(menu);
        menu.add(new MenuModel());

        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    } 

    @RequestMapping(value="/obat/tambah}", params={"addRow"}, method = RequestMethod.POST)
    public String addRow(RestoranModel restoran, MenuModel menu, BindingResult bindingResult, Model model) {
        if (restoran.getListMenu() == null) {
            restoran.setListMenu(new ArrayList<MenuModel>());
        }
        restoran.getListMenu().add(menu);
        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    }
    
    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.POST, params={"save"})
    private String addMenuSubmit(@ModelAttribute RestoranModel restoran, Model model) {
        RestoranModel archive = restoranService.getRestoranByIdRestoran(restoran.getIdRestoran()).get();
        for (MenuModel menu : restoran.getListMenu()) {
            menu.setRestoran(archive);
            menuService.addMenu(menu);
        }
        return "add-menu";
    }


    /*URL mapping view
    @RequestMapping(path = "/obat/view", method = RequestMethod.GET)
    public String view(
        // Request Parameter untuk dipass
        @RequestParam(value = "idObat") Long idObat, Model model
    ) {
        try {
            // Mengambil objek ObatModel yang dituju
            ObatModel obat = obatService.getObatByIdObat(idObat).get();
            
            List<MenuModel> menuList = menuService.findListMenuOrderByHargaAsc(obat.getIdObat());
            obat.setListMenu(menuList);

            //Add model obat ke "resto" untuk dirender
            model.addAttribute("resto", obat);

            // Return view template
            return "view-obat";
        } catch (NoSuchElementException x) {
            return "failed";
        }
    }

    @RequestMapping(path = "/obat/view-all")
    public String viewall(Model model) {
        // Mengambil objek ObatModel yang dituju
        List<ObatModel> restoList = obatService.getObatList();
        //Add model obat ke "restod" untuk dirender
        model.addAttribute("restoList", restoList);
        System.out.println(restoList);
        
        // Return view template
        return "viewall-obat";
    }

    // API yang digunakan untuk menuju halaman form change obat
    @RequestMapping(value="obat/change/{idObat}", method = RequestMethod.GET)
    public String changeObatFormPage(@PathVariable Long idObat, Model model) {
        //Mengambil existing data obat
        ObatModel existingObat = obatService.getObatByIdObat(idObat).get();
        model.addAttribute("obat", existingObat);
        model.addAttribute("page-title", "Change Obat");
        return "form-change-obat";
    }

    // API yang digunakan untuk submit form change obat
    @RequestMapping(value="obat/change/{idObat}", method = RequestMethod.POST)
    public String changeObatFormSubmit(@PathVariable Long idObat, @ModelAttribute ObatModel obat, Model model) {
        ObatModel newObatData = obatService.updateObat(obat);
        model.addAttribute("obat", newObatData);
        return "change-obat";
    }

    /* API yang digunakan untuk menghapus obat
    @RequestMapping(value = "/obat/delete/{idObat}", method = RequestMethod.GET)
    public String deleteObat(@PathVariable Long idObat,
                                Model model) {
        List<MenuModel> menu = menuService.findAllMenuByIdObat(idObat);
        if (menu.size() == 0) {
            obatService.deleteObat(idObat);
            return "success";
        }
        return "failedNoMenu";
    }*/
