package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 従業員関連機能の処理の制御を行うコントローラ.
 *
 * @author sota.akahane
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員一覧を出力する.
     *
     * @param model リクエストスコープ
     * @return 従業員一覧画面にフォワード
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList
                = employeeService.showList();
        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form) {
        int intId = Integer.parseInt(id);
        Employee employee = employeeService.showDetail(intId);
        model.addAttribute("employee", employee);

        return "employee/detail";
    }
}
