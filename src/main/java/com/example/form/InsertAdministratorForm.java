package com.example.form;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * 管理者登録時に使用するフォーム.
 *
 * @author sota.akahane
 */
public class InsertAdministratorForm {
    /** 名前 */
    @NotBlank(message = "氏名を入力してください。")
    private String name;
    /** メールアドレス */
    @NotBlank(message = "メールアドレスを入力してください。")
    private String mailAddress;
    /** パスワード */
    @Length(min = 4, max = 50, message = "パスワードは4文字以上50文字以内で入力してください。")
    private String password;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InsertAdministratorForm{" +
                "name='" + name + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
