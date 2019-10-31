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
import apap.tugas.sibat.repository.GudangDb;
import apap.tugas.sibat.service.*;

@Controller
public class GudangController {
    @Qualifier("gudangServiceImpl")
    @Autowired
    private GudangService gudangService;
    
    @Autowired
    private ObatService obatService;

    @RequestMapping(value = "/gudang", method = RequestMethod.GET) 
    public String home(Model model) {
    	List<GudangModel> gudangList = gudangService.getGudangList();
    	model.addAttribute("gudangList", gudangList);
        return "viewall-gudang";
    }
    
  //URL mapping view
    @RequestMapping(path = "/gudang/view", method = RequestMethod.GET)
    public String view(
        // Request Parameter untuk dipass
        @RequestParam(value = "idGudang") Long idGudang, @ModelAttribute("chosenObat") ObatModel chosenObat,  Model model
    ) {
        try {
            GudangModel gudang = gudangService.getGudangByIdGudang(idGudang).get();
            List<ObatModel> obatAvailable = obatService.getObatList();
            model.addAttribute("gudang", gudang);
            model.addAttribute("chosenObat", chosenObat);
            model.addAttribute("obatList", gudang.getListObat());
            model.addAttribute("jumlahObat", gudang.getJumlahObat());
            model.addAttribute("obatAvailable", obatAvailable);
            // Return view template
            return "view-gudang";
        } catch (NoSuchElementException x) {
            return "failed";
        }
    }
    
    @RequestMapping(path="/gudang/tambah-obat", method = RequestMethod.POST)
    public String assignObat(@RequestParam(value = "idGudang") Long idGudang, @ModelAttribute("chosenObat") ObatModel chosenObat, Model model) {
    	GudangModel gudang = gudangService.getGudangByIdGudang(idGudang).get();
        ObatModel obat = obatService.getObatByIdObat(chosenObat.getIdObat()).get();
        List<ObatModel> obatInGudang = gudang.getListObat();

        for (ObatModel find : obatInGudang) {
        	if (find.getIdObat().equals(obat.getIdObat())) {
        		return "failed-assign-obat";
        	}
        }
        gudangService.assignObat(gudang, obat);
        model.addAttribute("gudang", gudang);
        return "assign-obat-gudang";
    }
    
    @RequestMapping(value = "/gudang/tambah", method = RequestMethod.GET)
    public String addGudangFormPage(Model model) {
        GudangModel gudang = new GudangModel();
        model.addAttribute("gudang", gudang);
        return "form-add-gudang";
    }
    
    @RequestMapping(value = "/gudang/tambah", method = RequestMethod.POST)
    public String addGudangFormPageSubmit(@ModelAttribute GudangModel gudang, Model model) {
    	gudangService.addGudang(gudang);
        model.addAttribute("gudang", gudang);
        return "add-gudang";
    }
    
    @RequestMapping(value = "/gudang/hapus/{idGudang}", method = RequestMethod.GET)
    public String deleteGudang(@PathVariable Long idGudang,
                                Model model) {
    	GudangModel gudang = gudangService.getGudangByIdGudang(idGudang).get();
        List<ObatModel> obatInGudang = gudang.getListObat();
        for (ObatModel a : obatInGudang) {
        System.out.println(a.getNama());
        }
        if (obatInGudang.size() == 0) {
            gudangService.deleteGudang(idGudang);
            return "success";
        }
        return "failed-hapus-gudang";
    }
    
}
    
    /*@RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.POST, params={"save"})
    private String addMenuSubmit(@ModelAttribute RestoranModel restoran, Model model) {
        RestoranModel archive = restoranService.getRestoranByIdRestoran(restoran.getIdRestoran()).get();
        for (MenuModel menu : restoran.getListMenu()) {
            menu.setRestoran(archive);
            menuService.addMenu(menu);
        }
        return "add-menu";
    }
    
    @RequestMapping(path="/gudang/tambah-obat", method = RequestMethod.POST)
    public String assignObat(@ModelAttribute GudangModel gudang, Long idObat, Model model) {
        if (gudang.getListObat() == null) {
            gudang.setListObat(new ArrayList<ObatModel>());
        }
        
        List<ObatModel> obatInGudang = gudang.getListObat();
        for (ObatModel find : obatInGudang) {
        	if (find.getIdObat().equals(idObat)) {
        		return "success";
        	}
        }
        ObatModel obatFound = obatService.getObatByIdObat(idObat).get();
        gudang.getListObat().add(obatFound);
        model.addAttribute("gudang", gudang);
        return "assign-obat-gudang";
    }*/

    /*// URL mapping yang digunakan untuk mengakses halaman add gudang
    @RequestMapping(value = "/gudang/tambah", method = RequestMethod.GET)
    public String addGudangFormPage(Model model) {
        GudangModel gudang = new GudangModel();
        model.addAttribute("gudang", gudang);
        return "form-add-gudang";
    }
    
    @RequestMapping(value="/gudang/tambah}", params={"addRow"}, method = RequestMethod.POST)
    public String addRow(SupplierModel supplier, GudangModel gudang, BindingResult bindingResult, Model model) {
        if (gudang.getListSupplier() == null) {
            restoran.setListMenu(new ArrayList<MenuModel>());
        }
        restoran.getListMenu().add(menu);
        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    }

    //URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add gudang
    @RequestMapping(value = "/gudang/add", method = RequestMethod.POST)
    public String addGudangSubmit(@ModelAttribute GudangModel gudang, Model model) {
    	gudang.generateKode();
        gudangService.addGudang(gudang);
        /*model.addAttribute("kodeGudang", gudang.getKode());
        model.addAttribute("penerbitGudang", gudang.getPenerbit());
        model.addAttribute("pengarangGudang", gudang.getPengarang());
        model.addAttribute("judulGudang", gudang.getJudul());
        model.addAttribute("jenisGudang", gudang.getJenis());
        model.addAttribute("tanggalTerbitGudang", gudang.getTanggalTerbit());
        model.addAttribute("tahun", gudang.getTanggalTerbitInYear());
        model.addAttribute("abstrakGudang", gudang.getAbstrak());
        model.addAttribute("statusGudang", gudang.getStatus());
        return "add-gudang";
    }
    
    @RequestMapping(value = "/gudang/tambah}", method = RequestMethod.GET)
    public String addProductFormPage(@PathVariable(value="idRestoran") Long idRestoran, Model model) {
        ArrayList<MenuModel> menu = new ArrayList<>();
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        restoran.setListMenu(menu);
        menu.add(new MenuModel());

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
    @RequestMapping(path = "/gudang/view", method = RequestMethod.GET)
    public String view(
        // Request Parameter untuk dipass
        @RequestParam(value = "idGudang") Long idGudang, Model model
    ) {
        try {
            // Mengambil objek GudangModel yang dituju
            GudangModel gudang = gudangService.getGudangByIdGudang(idGudang).get();
            
            List<MenuModel> menuList = menuService.findListMenuOrderByHargaAsc(gudang.getIdGudang());
            gudang.setListMenu(menuList);

            //Add model gudang ke "resto" untuk dirender
            model.addAttribute("resto", gudang);

            // Return view template
            return "view-gudang";
        } catch (NoSuchElementException x) {
            return "failed";
        }
    }

    @RequestMapping(path = "/gudang/view-all")
    public String viewall(Model model) {
        // Mengambil objek GudangModel yang dituju
        List<GudangModel> restoList = gudangService.getGudangList();
        //Add model gudang ke "restod" untuk dirender
        model.addAttribute("restoList", restoList);
        System.out.println(restoList);
        
        // Return view template
        return "viewall-gudang";
    }

    // API yang digunakan untuk menuju halaman form change gudang
    @RequestMapping(value="gudang/change/{idGudang}", method = RequestMethod.GET)
    public String changeGudangFormPage(@PathVariable Long idGudang, Model model) {
        //Mengambil existing data gudang
        GudangModel existingGudang = gudangService.getGudangByIdGudang(idGudang).get();
        model.addAttribute("gudang", existingGudang);
        model.addAttribute("page-title", "Change Gudang");
        return "form-change-gudang";
    }

    // API yang digunakan untuk submit form change gudang
    @RequestMapping(value="gudang/change/{idGudang}", method = RequestMethod.POST)
    public String changeGudangFormSubmit(@PathVariable Long idGudang, @ModelAttribute GudangModel gudang, Model model) {
        GudangModel newGudangData = gudangService.updateGudang(gudang);
        model.addAttribute("gudang", newGudangData);
        return "change-gudang";
    }

    /* API yang digunakan untuk menghapus gudang
    @RequestMapping(value = "/gudang/delete/{idGudang}", method = RequestMethod.GET)
    public String deleteGudang(@PathVariable Long idGudang,
                                Model model) {
        List<MenuModel> menu = menuService.findAllMenuByIdGudang(idGudang);
        if (menu.size() == 0) {
            gudangService.deleteGudang(idGudang);
            return "success";
        }
        return "failedNoMenu";
    }*/
