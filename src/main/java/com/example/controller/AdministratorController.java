package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者関連機能の処理の制御を行うコントローラ.
 *
 * @author sota.akahane
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    /**
     * 管理者登録画面に遷移する.
     *
     * @param form リクエストパラメータが格納されたフォーム
     * @return 管理者登録画面にフォワード
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する.
     *
     * @param form リクエストパラメータが格納されたフォーム
     * @return ログイン画面にリダイレクト
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);

        return "redirect:/";
    }

    /**
     * ログイン画面に遷移する.
     *
     * @param form ログイン画面で渡されたリクエストパラメータが格納されたフォーム
     * @return ログイン画面にフォワード
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * ログイン機能.
     *
     * @param form  フォーム
     * @param model リクエストスコープ
     * @return 入力値が正しければ従業員一覧画面にフォワード
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator administrator
                = administratorService.login(form.getMailAddress(),
                form.getPassword());

        if (administrator == null) {
            String errorMessage = "メールアドレスまたはパスワードが不正です。";
            model.addAttribute("errorMessage", errorMessage);
            return "administrator/login";
        }

        session.setAttribute("administratorName", administrator.getName());

        return "redirect:/employee/showList";
    }
}
