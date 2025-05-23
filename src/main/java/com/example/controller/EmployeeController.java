package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private HttpSession session;

    /**
     * 従業員一覧を出力する.
     * ログインしていなければ閲覧できない。
     *
     * @param model リクエストスコープ
     * @return 従業員一覧画面にフォワード
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        // 存在するURLのヒントを与えないために、エラーメッセージ無しで単にログイン画面に戻す。
        // URLをトップに戻すために、フォワードではなくリダイレクトにする。
//        if (session.getAttribute("administratorName") == null) {
//            return "redirect:/";
//        }

        List<Employee> employeeList
                = employeeService.showList();
        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

    /**
     * 従業員詳細ページを出力.
     *
     * @param id    クリックされたリンクの従業員ID
     * @param model リクエストスコープ
     * @param form  フォーム
     * @return 従業員詳細ページにフォワード
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form) {
//        if (session.getAttribute("administratorName") == null) {
//            return "redirect:/";
//        }

        int intId = Integer.parseInt(id);
        Employee employee = employeeService.showDetail(intId);

        BeanUtils.copyProperties(employee, form);
        form.setId(id);
        form.setSalary(Integer.toString(employee.getSalary()));
        form.setDependentsCount(Integer.toString(employee.getDependentsCount()));

        model.addAttribute("employee", employee);
        model.addAttribute("updateEmployeeForm", form);

        return "employee/detail";
    }

    /**
     * 従業員情報を更新する.
     *
     * @param form フォーム
     * @return 従業員リストを出力する画面にフォワード
     */
    @PostMapping("/update")
    public String update(@Validated UpdateEmployeeForm form,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            System.out.println("koko");
            return showDetail(form.getId(), model, form);
        }

        Employee employee
                = employeeService.showDetail(form.getIntId());
        employee.setName(form.getName());
        employee.setSalary(form.getIntSalary());
        employee.setDependentsCount(form.getIntDependentsCount());
        employeeService.update(employee);

        return "redirect:/employee/showList";
    }
}
