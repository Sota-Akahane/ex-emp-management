package com.example.form;

import jakarta.validation.constraints.Pattern;

/**
 * 従業員更新時に使用するフォーム.
 *
 * @author sota.akahane
 */
public class UpdateEmployeeForm {
    /** 従業員ID */
    private String id;
    /** 扶養人数 */
    @Pattern(regexp = "^\\d+$", message = "数値を入力してください。")
    private String dependentsCount;

    public Integer getIntId() {
        return Integer.parseInt(id);
    }

    public Integer getIntDependentsCount() {
        return Integer.parseInt(dependentsCount);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }

    @Override
    public String toString() {
        return "UpdateEmployeeForm{" +
                "id='" + id + '\'' +
                ", dependentsCount='" + dependentsCount + '\'' +
                '}';
    }
}
