package com.example.housingcooperative;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HousingCooperativeController {

    HousingCooperativeRepository hcr;
    FlatRepository fr;
    ResidentRepository rr;

    public HousingCooperativeController(HousingCooperativeRepository hcr, FlatRepository fr, ResidentRepository rr) {
        this.hcr = hcr;
        this.fr = fr;
        this.rr = rr;
    }

    @GetMapping("/main")
    public String showMain(Model model) {
        model.addAttribute("hcCount", hcr.count());
        model.addAttribute("flatsCount", fr.count());
        model.addAttribute("residents", rr.count());
        return "index";
    }

    @GetMapping("/allHousingCooperatives")
    public String showHousingCooperativesList(Model model) {

        List<HousingCooperative> hcl = hcr.findAll();
        City[] cities = City.values();

        model.addAttribute("hcl", hcl);
        model.addAttribute("newHC", new HousingCooperative());
        model.addAttribute("cities", cities);

        return "houseCooperativesList";
    }

    @PostMapping("/addHouseCooperative")
    public String addHouseCooperative(HousingCooperative hc) {
        hcr.save(hc);
        return "redirect:/allHousingCooperatives";
    }

    @GetMapping("/houseCooperativeDetail")
    public String housingCoopDetail(Model model, @RequestParam Long idHC) {

        List<Flat> flatList = fr.findAllByHousingCooperative_IdHC(idHC);

        Optional<HousingCooperative> hcOptional = hcr.findById(idHC);


        if (hcOptional.isPresent()) {
            HousingCooperative hc = hcOptional.get();

            double sumArea = flatList.isEmpty() ? 0 : fr.sumFlatAreaByIdHC(hc);

            model.addAttribute("hc", hc);
            model.addAttribute("sumArea", sumArea);
            model.addAttribute("allFlats", flatList);
            model.addAttribute("flatsNo", flatList.size());
            model.addAttribute("newFlat", new Flat());

        } else {
            return "redirect:/allHousingCooperatives";
        }

        return "hcDetail";
    }

    @GetMapping("/deleteHC")
    public String deleteHC(@RequestParam Long idHC) {

        Optional<HousingCooperative> hcOptional = hcr.findById(idHC);
        HousingCooperative hc = null;

        if (hcOptional.isPresent()) {
            hc = hcOptional.get();
        }
        boolean residentResidual = false;
        List<Flat> flats = null;
        if (hc != null) {
            flats = fr.findAllByHousingCooperative_IdHC(hc.getIdHC());

            for (Flat flat : flats) {
                if (!rr.findAllByFlat_IdF(flat.getIdF()).isEmpty()) {
                    residentResidual = true;
                    System.out.println(flat.getIdF());
                    break;
                }
            }
        }

        if (hc != null && !residentResidual) {
            for (Flat flat : flats) {
                fr.delete(flat);
            }
            hcr.delete(hc);
        } else {
            return "info";
        }

        return "redirect:/allHousingCooperatives";
    }

    @GetMapping("/hcModifyForm")
    public String getModifyForm(@RequestParam Long idHC, Model model){

        Optional<HousingCooperative> hcOptional = hcr.findById(idHC);
        City [] cities = City.values();

        if(hcOptional.isPresent()){
            model.addAttribute("hcMod", hcOptional.get());
            model.addAttribute("cities", cities);
        } else {
            return "redirect:/main";
        }
        return "hcModify";
    }

    @PostMapping("/hcModify")
    public String modifyHC(@RequestParam Long idHC, @RequestParam String name, @RequestParam String street,
                           @RequestParam String no, @RequestParam City city, @RequestParam String post){

        Optional<HousingCooperative> hcOptional = hcr.findById(idHC);
        HousingCooperative hc = null;

        if (hcOptional.isPresent()) {
            hc = hcOptional.get();
        }

        if(!name.equals("")){
            hc.setName(name);
        }
        if(!street.equals("")){
            hc.setAdressStreet(street);
        }
        if(!no.equals("")){
            hc.setAdressNo(no);
        }
        if(city != null){
            hc.setCity(city);
        }
        if(!post.equals("")){
            hc.setAdressCode(post);
        }

        hcr.save(hc);

       return "redirect:/houseCooperativeDetail?idHC=" + hc.getIdHC();
    }

    @GetMapping("/flatDetail")
    public String flatDetail(Model model, @RequestParam Long idF) {

        List<Resident> residentList = rr.findAllByFlat_IdF(idF);
        Sex[] sexes = Sex.values();
        Optional<Flat> flatOptional = fr.findById(idF);

        if (flatOptional.isPresent()) {
            Flat flat = flatOptional.get();
            model.addAttribute("flat", flat);
            model.addAttribute("allResidents", residentList);
            model.addAttribute("residentsNo", residentList.size());
            model.addAttribute("newResident", new Resident());
            model.addAttribute("sexOption", sexes);
        } else {
            return "redirect:/allFlats";
        }

        return "flDetail";
    }

    @RequestMapping("/allFlats")
    public String showFlatsList(Model model) {
        List<Flat> flats = fr.findAll();
        List<HousingCooperative> hcList = hcr.findAll();

        model.addAttribute("flats", flats);
        model.addAttribute("newFlat", new Flat());
        model.addAttribute("hcList", hcList);

        return "flatsList";
    }

    @PostMapping("/addFlat")
    public String addFlat(Flat flat, @RequestParam Long idHC) {

        Optional<HousingCooperative> hcOptional = hcr.findById(idHC);

        if (hcOptional.isPresent()) {
            HousingCooperative hc = hcOptional.get();
            flat.setHousingCooperative(hc);
            fr.save(flat);
        } else {
            return "redirect:/main";
        }

        return "redirect:/allFlats";
    }


    @GetMapping("/deleteF")
    public String deleteFlat(@RequestParam Long idF) {

        Optional<Flat> flatOptional = fr.findById(idF);

        boolean residentResidual = true;
        if (rr.findAllByFlat_IdF(idF).isEmpty()) {
            residentResidual = false;
        }

        if (flatOptional.isPresent() && !residentResidual) {
            Flat flat = flatOptional.get();
            fr.delete(flat);
        } else {
            return "info.html";
        }

        return "redirect:/allFlats";
    }

    @RequestMapping("/allResidents")
    public String showResidentsList(Model model) {
        List<Resident> residents = rr.findAll();
        List<Flat> flatsList = fr.findAll();
        Sex[] sexes = Sex.values();
        model.addAttribute("residents", residents);
        model.addAttribute("newResident", new Resident());
        model.addAttribute("flatsList", flatsList);
        model.addAttribute("sexOption", sexes);

        return "residentsList";
    }

    @PostMapping("/addResident")
    public String addResident(Resident resident, @RequestParam Long idF) {

        Optional<Flat> flatOptional = fr.findById(idF);

        if (flatOptional.isPresent()) {
            Flat flat = flatOptional.get();
            flat.setResidentsNumber(flat.getResidentsNumber() + 1);
            fr.save(flat);
            resident.setFlat(flat);
            rr.save(resident);
        } else {
            return "redirect:/main";
        }

        return "redirect:/allResidents";
    }

    @GetMapping("/residentDetail")
    public String residentDetail(Model model, @RequestParam Long idR) {

        Optional<Resident> residentOptional = rr.findById(idR);

        if (residentOptional.isPresent()) {
            Resident resident = residentOptional.get();
            model.addAttribute("resident", resident);
        } else {
            return "redirect:/allResidents";
        }

        return "rsDetail";
    }


    @GetMapping("/deleteR")
    public String deleteResident(@RequestParam Long idR) {

        Optional<Resident> residentOptional = rr.findById(idR);

        if (residentOptional.isPresent()) {
            Resident resident = residentOptional.get();
            rr.delete(resident);
        } else {
            return "redirect:/main";
        }

        return "redirect:/allResidents";
    }
}
