package com.rainier.beans;

import java.util.List;

public class UpdateLeaveRequest {

    private List<LeaveRequestBean> leaveBean;
    private Integer modifiedBy;

    public List<LeaveRequestBean> getLeaveBean() {
        return leaveBean;
    }

    public void setLeaveBean(List<LeaveRequestBean> leaveBean) {
        this.leaveBean = leaveBean;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
