package com.czwx.imall.system.domain;

import java.util.List;

public class SysMenu {
    private String menuId; // 菜单编号
    private String menuName; //菜单名称
    private String superiorId;// 上级id
    private String linkUrl;//连接
    private String dispImage;//
    private int menuOrder;//菜单顺序
    private String permValid;//
    private String validAction;//
    private String validOper;//操作员
    private int status;//状态，0禁用，1正常
    private String remark;//
    private String isMenu;
    private String thisclass;//选择的样式


    public String getThisclass() {
        return thisclass;
    }
    public void setThisclass(String thisclass) {
        this.thisclass = thisclass;
    }
    private List<SysMenu> melist;//


    public List<SysMenu> getMelist() {
        return melist;
    }
    public void setMelist(List<SysMenu> melist) {
        this.melist = melist;
    }
    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getSuperiorId() {
        return superiorId;
    }
    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }
    public String getLinkUrl() {
        return linkUrl;
    }
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    public String getDispImage() {
        return dispImage;
    }
    public void setDispImage(String dispImage) {
        this.dispImage = dispImage;
    }

    public int getMenuOrder() {
        return menuOrder;
    }
    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
    }
    public String getPermValid() {
        return permValid;
    }
    public void setPermValid(String permValid) {
        this.permValid = permValid;
    }
    public String getValidAction() {
        return validAction;
    }
    public void setValidAction(String validAction) {
        this.validAction = validAction;
    }
    public String getValidOper() {
        return validOper;
    }
    public void setValidOper(String validOper) {
        this.validOper = validOper;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }
}
