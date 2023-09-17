package com.rainier.beans;

import java.util.List;

public class SubMenuResponseBean {
    private boolean status;
    private String message;
    private List<ModulesBean> menusList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ModulesBean> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<ModulesBean> menusList) {
        this.menusList = menusList;
    }
}
