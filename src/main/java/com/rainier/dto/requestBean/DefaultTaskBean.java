package com.rainier.dto.requestBean;

public class DefaultTaskBean {
    private int id;
    private String defaultTask;
    private Integer is_default;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefaultTask() {
        return defaultTask;
    }

    public void setDefaultTask(String defaultTask) {
        this.defaultTask = defaultTask;
    }

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }


}
