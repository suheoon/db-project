package com.db451B07.HW4.company;

import com.db451B07.HW4.company.model.DeleteEmployeeReq;
import com.db451B07.HW4.company.model.Employee;
import com.db451B07.HW4.company.model.GetSearchResult;
import com.db451B07.HW4.company.model.UpdateEmployeeReq;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class CompanyController {
    private CompanyService companyService = new CompanyService();

    @RequestMapping
    public String home() {
        return "home";
    }

    @GetMapping("/search")
    private String search(Model model, @RequestParam Map<String, String> params) {
        ArrayList<GetSearchResult> searchResults = companyService.searchEmployee(params);

        model.addAttribute("option", companyService.getOption());
        model.addAttribute("tableHeaders", companyService.getHeader());
        model.addAttribute("searches", searchResults);

        return "search";
    }

    @GetMapping("/search/family")
    private String searchFamily(Model model, @RequestParam String ssn) {
        ArrayList<String> searchResults = companyService.searchFamily(ssn);
        model.addAttribute("name", searchResults);

        return "familySearch";
    }

    @PostMapping("/delete")
    private String delete(@ModelAttribute DeleteEmployeeReq deleteEmployeeReq) {
        companyService.deleteEmployee(deleteEmployeeReq);

        return "home";
    }

    @PostMapping("/update")
    private String update(@ModelAttribute UpdateEmployeeReq updateEmployeeReq) {
         companyService.updateEmployee(updateEmployeeReq);

         return "home";
    }

    @PostMapping ("/insert")
    private String add(@ModelAttribute Employee employee) {
        companyService.insertEmployee(employee);

        return "home";
    }

    //    @GetMapping("/insertPage")
//    private String insertPage(){
//        return "/jsp/insert.jsp";
//    }
//
//    @PostMapping("/insert")
//    private String insert(@ModelAttribute Employee employee){
//        boolean insert = companyService.
//        if(insert) return "/jsp/home.jsp";
//        else return "/jsp/insertFail.jsp";
//    }


}
